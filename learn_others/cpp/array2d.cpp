#include <iostream>
using namespace std;

int main () {
  int mat[2][3] = {{1, 2, 3} , {4, 5,6}};

  for (int r=0; r < sizeof(mat)/sizeof(mat[0]); r++) {
    for (int c=0; c < sizeof (mat[r])/sizeof(mat[r][0]); c++) {
      cout << "mat [" << r << "] [" << c << "] = " << mat[r][c] << endl;
    }
  }

  return 0;
}
