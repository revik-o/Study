#include "stdlib.h"
#include "time.h"

int rand_int(int range) {
     srand(time(NULL));
     return rand();
}