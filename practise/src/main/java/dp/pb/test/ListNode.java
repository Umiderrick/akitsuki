/**
 * 
 */
package dp.pb.test;

/**
 * @author pengbo
 *
 */
public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}
	
	
	public ListNode reverseListNode(ListNode head){
		if(head ==null)
			return head;
		ListNode prev =head;
		ListNode cur =head.next;
		ListNode tmp;
		while(cur !=null){
			tmp =cur.next;
			cur.next=prev;
			prev=cur;
			cur=tmp;
		}
		head.next=null;
		return prev;
	}
}
