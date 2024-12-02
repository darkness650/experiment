#include<iostream>
#include<cmath>
using namespace std;
int maxsize=0;
void shemian(int size, bool* p)
{
	if (size == 0) return;
	for (int i = 0; i < size / 2; i++)
	{
		for (int j = 0; j < size / 2; j++)
		{
			p[maxsize * i + j] = 0;
		}
	}
	shemian(size / 2, p + size / 2);
	shemian(size / 2, p + maxsize * size / 2);
	shemian(size / 2, p + maxsize * size / 2 + size / 2);
}
int main()
{
	int n;
	cin >> n;
	maxsize = pow(2, n);
	bool* p = new bool[maxsize * maxsize];
	for (int i = 0; i < maxsize * maxsize; i++)
	{
		p[i] = 1;
	}
	shemian(maxsize, p);
	for (int i = 0; i < maxsize * maxsize; i++)
	{
		cout << p[i];
		if ((i + 1) % (maxsize) != 0);//cout << " ";
		else if (i != maxsize * maxsize - 1 ) cout << endl;
	}
	return 0;
}
