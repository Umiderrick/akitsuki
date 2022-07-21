package im.vinci.core.webutils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * SpringBean监控切面
 * 
 * @author OSWorks-XC
 * @since 2010-09-21
 */
public class SpringBeanAspect {

	private static Log log = LogFactory.getLog(SpringBeanAspect.class);

	/**
	 * SpringBean方法调用通知
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object interceptCall(ProceedingJoinPoint pjp) throws Throwable {
		String clazzString = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String fullPath = clazzString + "." + methodName;
		int flag = clazzString.indexOf("$");
		if (flag < 0)
			log.info("开始业务处理[" + methodName + "];全路径[" + fullPath + "]");
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		if (flag < 0)
			log.info("结束业务处理[" + methodName + "];耗时:" + time + "毫秒;全路径[" + fullPath + "]");
		return retVal;
	}
}
