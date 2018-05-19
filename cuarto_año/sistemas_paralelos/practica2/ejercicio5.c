#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#include<sys/time.h>

// Declaraciones de variables
int N = 2048; // Dimensión por defecto del arreglo
int *vector;
int suma_global = 0;
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
void * sumador(void * arg){
    int i;
    int suma_local = 0;
    int id_thread = * (int *) arg;
    int base =  elementos_por_thread * id_thread;
    int limite = elementos_por_thread * (id_thread+1); 

    for(i = base; i < limite; i++){
        suma_local+= vector[i];
    }

    pthread_mutex_lock(&mutex);
        suma_global+=suma_local;
    pthread_mutex_unlock(&mutex);
    pthread_exit(0);
}

int main(int argc,char*argv[]){

    double timetick;

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
        vector[i] = i+1;
    }

    timetick = dwalltime(); // Empieza a contar el tiempo
    pthread_mutex_init(&mutex, NULL); // Inicializa mutex con valor por defecto

    //Crea los threads 
    for(i = 0; i < NUM_THREADS ; i++){
        id_threads[i]=i;
        pthread_create(&threads[i], NULL, &sumador, &id_threads[i]);
    }
 
    // El hilo llamador espera a que todos terminen de buscar la cantidad de pares en su sub-arreglo.
    for(i = 0; i < NUM_THREADS; i++){
        pthread_join(threads[i], NULL);
    }

    double promedio = suma_global / N;

    printf("Tiempo en segundos %f\n", dwalltime() - timetick); // Informa el tiempo
    printf("El promedio es: %f\n", promedio);

    free(vector);
    pthread_mutex_destroy(&mutex);
    return(0);
}