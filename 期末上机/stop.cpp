#include<iostream>
using namespace std;
int main()
{
	int a, n, m, x,getin,output;
	cin >> a >> n >> m >> x;
	int* fei = new int[n];
	fei[0] = 1;
	fei[1] = 1;
	if (x == 2 || x == 1)
	{
		cout << a; return 0;
	}
	if (x == 3)
	{
		cout<<2*a; return 0;
	}
	int xishu1 = 2, xishu2 = 0;
	for (int i = 2; i < n; i++)
	{
		fei[i] = fei[i - 1] + fei[i - 2];
	}
	for (int i = 0; i < n - 5; i++)
	{
		xishu1 += fei[i];
	}
	xishu2 = xishu1 + fei[n - 5]-2;
	getin = (m - xishu1 * a) / xishu2;
	xishu1 = 2, xishu2 = 0;
	for (int i = 0; i < x - 4; i++)
	{
		xishu1 += fei[i];
	}
	xishu2 = xishu1 + fei[x - 4]-2;
	output = xishu1 * a + xishu2 * getin;
	cout << output;
	return 0;
}
