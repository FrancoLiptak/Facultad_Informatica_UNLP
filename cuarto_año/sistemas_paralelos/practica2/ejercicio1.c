#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#include<sys/time.h>

double *A,*B,*C;

//Dimension por defecto de las matrices
int N=100;
int num_threads=4;


void * multiplicar(void * arg);


//Para calcular tiempo
double dwalltime(){
        double sec;
        struct timeval tv;

        gettimeofday(&tv,NULL);
        sec = tv.tv_sec + tv.tv_usec/1000000.0;
        return sec;
}


int main(int argc,char*argv[]){

 int check=1;
 double timetick;
 N=atoi(argv[1]);
 num_threads=atoi(argv[2]);
 pthread_t threads[num_threads];
 int i,j,ids[num_threads];

 //Controla los argumentos al programa

 //Aloca memoria para las matrices
A=(double*)malloc(sizeof(double)*N*N);
B=(double*)malloc(sizeof(double)*N*N);
C=(double*)malloc(sizeof(double)*N*N); 

 //Inicializa las matrices A y B en 1, el resultado sera una matriz con todos sus valores en N
  for(i=0;i<N;i++){
   for(j=0;j<N;j++){
      A[i*N+j]=1;
      B[i*N+j]=1;
   }
  }   

  timetick = dwalltime();
//Crea los threads 
  for(i=0;i<num_threads;i++){
    ids[i]=i;
    pthread_create(&threads[i], NULL, multiplicar, &ids[i]);
  }
 //Realiza la multiplicacion



  for(i=0;i<num_threads;i++){
    pthread_join(threads[i], NULL);
  }

 printf("Tiempo en segundos %f\n", dwalltime() - timetick);

 //Verifica el resultado
  for(i=0;i<N;i++){
   for(j=0;j<N;j++){
  check=check&&(C[i*N+j]==N);
   }
  }   

  if(check){
   printf("Multiplicacion de matrices resultado correcto\n");
  }else{
   printf("Multiplicacion de matrices resultado erroneo\n");
  }

 free(A);
 free(B);
 free(C);
 return(0);
}

void * multiplicar(void * arg){
  int id = * (int *) arg;
  int base = N/num_threads;
  int i,j,k;
  for(i=id*base;i<base*(id+1);i++){
   for(j=0;j<N;j++){
    for(k=0;k<N;k++){
      C[i*N+j]= C[i*N+j] + A[i*N+k] * B[k+j*N];
    }
   }
  } 
  pthread_exit(0);
}