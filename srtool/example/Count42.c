// RUN: %tool "%s" > "%t"
// RUN: %diff %CORRECT "%t"
int s;

int foo(int a, int b)
<<<<<<< HEAD
requires a!=0,
requires b > 0,
ensures \result > \old(s)
=======

>>>>>>> branch 'master' of https://github.com/w460461339/softwareReliability.git
{

	int j;
	int i;
<<<<<<< HEAD
	i=2;
	i=1*2;
	s=0;
	s=0;
	s=0;
	s=0;
	s=0;
	
	return 1;
// 	i=501;
// 	assert(1==1);
// 	j=1;
// 	i=i/(j-j);
// // 	if (i == 0) {
// // 		j = 2;
// // 	}
// // 	else {
// // 		i = 1;
// // 	}
// 	if (j != 0) {
// 		j = 2;
// 	}
// 	else {
// 		i = 1;
// 	}
// 	return 0;

=======

	i = 1 << 5;
	return i;
>>>>>>> branch 'master' of https://github.com/w460461339/softwareReliability.git
}