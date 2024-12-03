#include<stdio.h>
int main()
{
	int a, n, m, x,num,y,i;
	int xishu1, xishu2 = 0;
	scanf("%d %d %d %d", &a, &n, &m, &x);
	xishu1 = 2*a;
	int fei(int x, int a);
	if (x == 2||x==1) 
	{
		printf("%d", a); return 0;
	}
	if (x == 3)
	{
		printf("%d", 2 * a); return 0;
	}
	n = n - 1;
	for (i = 1; i <= n -3; i++)
		xishu2 += fei(i, 1);
	for (i = 1; i <= n -4; i++)
		xishu1 += fei(i, a);
	y = (m - xishu1) / xishu2;
	xishu1 = 2*a;
	xishu2 = 0;
	for (i = 1; i <= x -3; i++)
		xishu2 += fei(i, 1);
	for (i = 1; i <= x - 4; i++)
		xishu1 += fei(i, a);
	num = xishu1 + xishu2 * y;
	printf("%d",num);
	return 0;
}
int fei(int x, int a)
{
	if (x == 1 || x == 2) return a;
	else return fei(x - 1, a) + fei(x - 2, a);
}
