package com.zs.pms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author lishuai 
 * ʱ�乤����
 */
public class DateUtil {

	/**
	 * ��õ�ǰʱ�䷽�����ַ�����
	 * 
	 * @return ���ص�ǰʱ���ַ���
	 */

	public static String getStrDate() {
		// �����������
		Calendar cal = Calendar.getInstance();
		// ��ǰ����
		int y = cal.get(Calendar.YEAR);
		// ��ǰ����(�·ݴ��㿪ʼ)
		int m = cal.get(Calendar.MONTH) + 1;
		// ��ǰ����
		int d = cal.get(Calendar.DAY_OF_MONTH);
		// ��ǰ��Сʱ
		int hh = cal.get(Calendar.HOUR_OF_DAY);
		// ��ǰ�ķ�
		int minute = cal.get(Calendar.MINUTE);
		// ��ǰ����
		int second = cal.get(Calendar.SECOND);
		//ͨ����õĵ�ǰСʱ���ж������磬���绹������
//		String str="";
//		if (hh>=6&&hh<=11) {
//			str="����ã�";
//		} else if(hh>11&&hh<=14){
//			str="����ã�";
//		}else if (hh>14&&hh<=18) {
//			str="����ã�";
//		}else if (hh>18&&hh<24) {
//			str="���Ϻã�";
//		}else {
//			str="��ʲô�棬��ȥ˯������";
//		}
		return y+"-"+m+"-"+d+" ";

	}

	/**
	 * �ַ���תdate�ķ���
	 * 
	 * @param time �ַ���          
	 * @param pattern �����ַ�����ʱ���ʽ       
	 * @return date��ʱ��
	 * @throws ParseException
	 */
	public static Date getStrToDate(String time, String pattern) throws ParseException {
		// ��ø�ʽ������
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// ���ظ�ʽ���õ�ʱ��
		return sdf.parse(time);
	}

	/**
	 * DateתString ʱ��ķ���
	 * @param time Date���ʱ��
	 * @param pattern ��ʱ���ʽ����ʲô��
	 * @return �ַ������ʱ��
	 */
	public static String getDateToStr(Date time, String pattern) {
		// ��ø�ʽ������
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// ���ظ�ʽ���õ�ʱ��
		return sdf.format(time);

	}
}
