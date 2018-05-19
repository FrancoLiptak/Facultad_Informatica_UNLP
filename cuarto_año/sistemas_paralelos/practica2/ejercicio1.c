#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#include<sys/time.h>

int N=128; //Dimension por defecto de las matrices será 128*128
int cantidad_threads = 2;
double *A,*B,*C; // * es para declarar punteros

//Para calcular tiempo
double dwalltime(){
        double sec;
        struct timeval tv;

        gettimeofday(&tv,NULL);
        sec = tv.tv_sec + tv.tv_usec/1000000.0;
        return sec;
}

void * multiplicacion(void *arg);

int main(int argc,char*argv[]){ // argc es un argumento tipo entero (contiene el número de argumentos que se introdujeron). argv es un argumento tipo array (array de punteros a caracteres)

  int check=1;
  double timetick;
  int i,j;

  //Controla los argumentos al programa
  if ((argc != 3) || ((N = atoi(argv[1])) <= 0) || ((cantidad_threads = atoi(argv[2])) <= 0) ){
    printf("Debe ingresar la dimesión de la matriz a trabajar y la cantidad de hilos");
    exit(1);
  }

  pthread_t threads[cantidad_threads];
  int id_threads[cantidad_threads];

  //Aloca memoria para las matrices
  A=(double*)malloc(sizeof(double)*N*N);
  B=(double*)malloc(sizeof(double)*N*N);
  C=(double*)malloc(sizeof(double)*N*N);

  //Inicializa las matrices A y B en 1, el resultado sera una matriz con todos sus valores en N. No es de nuestro interés reducir el tiempo en la inicialización.
  for(i=0;i<N;i++){
   for(j=0;j<N;j++){
      A[i*N+j]=1;
      B[i*N+j]=1;
   }
  }   
 
  timetick = dwalltime(); // Empieza a controlar el tiempo

  for( i = 0; i < cantidad_threads; i++ ){
    id_threads[i] = i;
    pthread_create(&threads[i], NULL, multiplicacion, &id_threads[i]);
  }

  for( i = 0; i < cantidad_threads; i++ ){
    pthread_join(threads[i], NULL);
  }

  printf("Tiempo en segundos %f\n", dwalltime() - timetick); // Informa el tiempo

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

void * multiplicacion(void *arg){

  int i,j,k;
  int id = * (int *) arg;
  int celdas_a_procesar = N / cantidad_threads;
  int base = id * celdas_a_procesar;
  int limite = celdas_a_procesar * (id+1);

  for(i = base; i < limite ; i++){
    for(j=0;j<N;j++){
      C[i+j*N]=0;
      for(k=0;k<N;k++){
        C[i+j*N]= C[i+j*N] + A[i+j*N] * B[i+j*N];
      }
    }
  }    

  pthread_exit(0);
}