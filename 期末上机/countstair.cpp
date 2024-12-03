#include<iostream>
using namespace std;
int main()
{
	int N;
	cin >> N;
	int siz = 0;
	int** p = new int* [N + 1];
	for (int i = 0; i < N + 1; i++)
	{
		p[i] = new int[1100];
		for (int j = 0; j < 1100; j++)
		{
			p[i][j] = -1;
		}
	}
	p[0][0] = 1; p[1][0] = 1;
	for (int i = 2; i < N + 1; i++)
	{
		for (int j = 0; p[i - 1][j] >= 0; j++)
		{
			if (p[i - 2][j] < 0) p[i][j] = p[i - 1][j];
			else p[i][j] = p[i - 1][j] + p[i - 2][j];
		}
		for (int j = 0; p[i][j] >= 0; j++)
		{
			if (p[i][j] >= 10)
			{
				if (p[i][j + 1] < 0) p[i][j + 1] = 1;
				else p[i][j + 1]++;
				p[i][j] -= 10;
			}
			if (i == N) siz = j;
		}
	}
	for (int size=siz; size >= 0; size--)
	  cout << p[N][size];
	return 0;
}
