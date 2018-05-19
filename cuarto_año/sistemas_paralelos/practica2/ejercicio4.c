#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#include<sys/time.h>
#include <stdlib.h>

// Declaraciones de variables
int N = 2048; // Dimensión por defecto del arreglo
int *vector;
int NUM_THREADS = 4;
int elementos_por_thread; // Cantidad de elementos que procesa cada thread
pthread_mutex_t mutex;

// Para calcular tiempo
double dwalltime(){
    double sec;
    struct timeval tv;

    gettimeofday(&tv,NULL);
    sec = tv.tv_sec + tv.tv_usec/1000000.0;
    return sec;
}

// Funcion para pasarle a los threads
void * orden(void * arg){ // Orden de menor a mayor
    int i,j;
    int aux_local;
    int id_thread = * (int *) arg;
    int base =  elementos_por_thread * id_thread;
    int limite = elementos_por_thread * (id_thread+1); 

    for(i = base; i < limite; i++){
        for(j = i+1; j < limite; j++){
            if(vector[i] > vector[j]){
                aux_local = vector[i];
                vector[i] = vector[j];
                vector[j] = aux_local;
            }
        }
    }

    pthread_exit(0);
}

int main(int argc,char*argv[]){

    double timetick;
    int aux, j;

    //Controla los argumentos al programa
    if ((argc != 3) || ((N = atoi(argv[1])) <= 0) || ((NUM_THREADS = atoi(argv[2])) <= 0)){
        printf("Debe ingresar la longitud del arreglo y la cantidad de threads. \n");
        exit(1);
    }

    pthread_t threads[NUM_THREADS];
    int i, id_threads[NUM_THREADS];
    elementos_por_thread = N / NUM_THREADS;

    // Alocación de memoria, e inicializiación del vector
    vector = (int*)malloc(sizeof(int)*N);
    for(i=0; i<N; i++){
        vector[i] = rand() % 10;
    }

    timetick = dwalltime(); // Empieza a contar el tiempo
    pthread_mutex_init(&mutex, NULL); // Inicializa mutex con valor por defecto

    //Crea los threads 
    for(i = 0; i < NUM_THREADS ; i++){
        id_threads[i]=i;
        pthread_create(&threads[i], NULL, orden, &id_threads[i]);
    }

    // El hilo llamador espera a que todos terminen de buscar la cantidad de pares en su sub-arreglo.
    for(i = 0; i < NUM_THREADS; i++){
        pthread_join(threads[i], NULL);
    }

    for(i = 0; i < N; i++){
        for(j = i+1; j < N; j++){
            if(vector[i] > vector[j]){
                aux = vector[i];
                vector[i] = vector[j];
                vector[j] = aux;
            }
        }
    }

    printf("Tiempo en segundos %f\n", dwalltime() - timetick); // Informa el tiempo

    // Verifica el resultado
    printf("El vector ordenado es:\n");

    for(i = 0; i < N; i++){
        printf("%d\n", vector[i]);
    }

    free(vector);
    pthread_mutex_destroy(&mutex);
    return(0);
}