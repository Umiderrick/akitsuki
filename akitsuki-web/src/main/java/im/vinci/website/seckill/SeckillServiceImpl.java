package im.vinci.website.seckill;


import im.vinci.core.exception.RepeatKillException;
import im.vinci.core.exception.SeckillCloseException;
import im.vinci.core.exception.SeckillException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by wchb7 on 16-5-14.
 */

@Service
public class SeckillServiceImpl implements SeckillService {


    //md5盐值字符串,用于混淆md5
    private final String slat = "asdfasd2341242@#$@#$%$%%#@$%#@%^%^";

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
//        Seckill seckill = getById(seckillId);
//        if (seckill == null) {
//            return new Exposer(false, seckillId);
//        }
        Date startTime = new Date();
        Date endTime = new Date();
        Date nowTime = new Date();
        if (nowTime.getTime() > endTime.getTime() || nowTime.getTime() < startTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转化特定字符串的过程,不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }


    @Transactional
    /**
     * 使用注解控制事务的优点:
     * 1.开发团队达成一致约定,明确标注事务方法的编程风格.
     * 2.保证事务方法的执行时间尽可能短,不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部.
     * 3.不是所有的方法都需要事务.如一些查询的service.只有一条修改操作的service.
     */
    @Override
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        if (StringUtils.isEmpty(md5) || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException(SeckillStatEnum.DATA_REWRITE.getStateInfo());
        }

        //执行秒杀逻辑:1.减库存.2.记录购买行为
        Date nowTime = new Date();

        try {
//            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                int updateCount = ThreadLocalRandom.current().nextInt();
            if (updateCount <= 0) {
                throw new SeckillCloseException(SeckillStatEnum.END.getStateInfo());
            } else {

                //记录购买行为
//                int inserCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                int inserCount = ThreadLocalRandom.current().nextInt();
                if (inserCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException(SeckillStatEnum.REPEAT_KILL.getStateInfo());
                } else {
                    //秒杀成功
//                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS);
                }
            }
        } catch (SeckillCloseException | RepeatKillException e1) {
            throw e1;
        } catch (Exception e) {
            //所有的编译期异常转化为运行期异常,spring的声明式事务做rollback
            throw new SeckillException("seckill inner error: " + e.getMessage());
        }


    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
