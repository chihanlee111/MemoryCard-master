
/**
 * All rights Reserved, Designed By Vongvia
 * @Title:  得到随机数的函数，且count个不重复	 
 * @author:	Vongvia  欢迎各位童鞋来交流 ：44125count5count3
 * @date:	2015.11.17
 * @version	V1.0
 */
package com.vongvia.acitivity;
import java.util.Random;

public class GetIndex {
    static int[] Getnum(int count)
    {

        int[] temp = new int[count *2];
        int[] temp_ = new int[count];

        int[] index = new int[count];
        int temp1;
        Random r = new Random();
        for (int i = 0; i < count; i++)
        {
            temp1 = r.nextInt(count);//0-5
            temp[i] = GetNumber(temp, i, 0, count, temp1, r);
        }

        for (int i = 0; i < count; i++)
        {
            temp_[i] = temp[i];
        }

        for (int i = 0; i < count; i++)
        {

            index[i] = r.nextInt(count);

            for (int j = i; j < count; j++)
            {
                if (index[j] == index[i])
                {
                    index[j] = r.nextInt(count);
                }
            }
        }


        int[] num = new int[count];
        for (int i = 0; i < num.length; i++)
        {
            num[i] = i + 1;
        }
        Random w= new Random();

        int[] result = new int[count];
        int max = count;
        for (int j = 0; j < result.length; j++)
        {

            int nindex = w.nextInt(max);

            result[j] = num[nindex]-1;



            num[nindex] = num[max - 1];

            max--;
        }

        for (int i = count; i < count*2; i++)
        {

            temp[i] = temp_[result[i-count]];
        }
        return temp;
    }
    static int GetNumber(int[] a, int index, int minValue, int maxValue, int temp, Random r)
    {
        for (int i = 0; i < index; i++)
        {
            if (a[i] == temp)
            {
                int newTemp = r.nextInt(maxValue)+minValue;
                a[index] = newTemp;
                return GetNumber(a, index, minValue, maxValue, newTemp, r);
            }
        }
        return temp;
    }


}
