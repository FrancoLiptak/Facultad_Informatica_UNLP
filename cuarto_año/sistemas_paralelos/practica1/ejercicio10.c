#include<stdio.h>
#include<stdlib.h>

double dwalltime();

int main(int argc, char* argv[]){
    int N=argv[1];
    int i=0;
    double *A;
    double timetick;

    A=(double*)malloc(sizeof(double)*N); // Aloca espacio para el primer arreglo.

    printf("Incializando arreglo...\n");
    for(i=0;i<N;i++){ // Solo sería necesario inicializar el primer arreglo. El contenido de los otros surgirá de las cuentas sobre estos valores.
        A[i*N]=i; // hay que pensar bien el manejo del arreglo en memoria
    }  

    // Aloca espacio para los arreglos nuevos, y desaloco el espacio para los arreglos que ya no se usen
    while(N/2 != 1){
        // arreglo_nuevo = (double*)malloc(sizeof(double)*(N/2)); Esto alocaría espacio para el nuevo arreglo.
        
        // for(i=0; i< arreglo_anterior; i+2) { arreglo_nuevo[i] = arreglo_anterior[i]/arreglo_anterior[i+1] }

        // free(arreglo_anterior)
    }

     // finalmente se imprimiría el uníco valor que queda en el arreglo (sería el resultado)
    
    free(A); // free del primer arreglo
}

    /*****************************************************************/

#include <sys/time.h>

double dwalltime(){
    double sec;
    struct timeval tv;

    gettimeofday(&tv,NULL);
    sec = tv.tv_sec + tv.tv_usec/1000000.0;
    return sec;
}
