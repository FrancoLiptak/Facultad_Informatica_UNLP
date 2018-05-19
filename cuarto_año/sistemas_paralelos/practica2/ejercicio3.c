#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#include<sys/time.h>

// Declaraciones de variables
int N = 2048; // Dimensión por defecto del arreglo
int *vector;
int numero_buscado;
int minimo_global = 99999999; // Cantidad de ocurrencias
int maximo_global = -1;
int NUM_THREADS = 4;
int elementos_por_thread; // Cantidad de elementos que procesa cada thread
pthread_mutex_t mutex_min, mutex_max;

// Para calcular tiempo
double dwalltime(){
    double sec;
    struct timeval tv;

    gettimeofday(&tv,NULL);
    sec = tv.tv_sec + tv.tv_usec/1000000.0;
    return sec;
}

// Funcion para pasarle a los threads
void * buscador_maximos_minimos(void * arg){
    int i;
    int maximo_local = -1;
    int minimo_local = 99999999;
    int id_thread = * (int *) arg;
    int base =  elementos_por_thread * id_thread;
    int limite = elementos_por_thread * (id_thread+1); 

    // Cuenta la cantidad de números pares
    for(i = base; i < limite; i++){
        if(vector[i] > maximo_local) maximo_local = vector[i];
        if(vector[i] < minimo_local) minimo_local = vector[i];
    }

    pthread_mutex_lock(&mutex_min);
        if(minimo_local < minimo_global) minimo_global = minimo_local;
    pthread_mutex_unlock(&mutex_min);
    pthread_mutex_lock(&mutex_max);
        if(maximo_local > maximo_global) maximo_global = maximo_local;
    pthread_mutex_unlock(&mutex_max);
    pthread_exit(0);
}

int main(int argc,char*argv[]){

    double timetick;

    //Controla los argumentos al programa
    if ((argc != 3) || ((N = atoi(argv[1])) <= 0) || ((NUM_THREADS = atoi(argv[2])) <= 0) ){
        printf("Debe ingresar la longitud del arreglo y la cantidad de threads. \n");
        exit(1);
    }

    pthread_t threads[NUM_THREADS];
    int i, id_threads[NUM_THREADS];
    elementos_por_thread = N / NUM_THREADS;

    // Alocación de memoria, e inicializiación del vector
    vector = (int*)malloc(sizeof(int)*N);
    for(i=0; i<N; i++){
        vector[i] = i;
    }

    timetick = dwalltime(); // Empieza a contar el tiempo
    pthread_mutex_init(&mutex_max, NULL); // Inicializa mutex con valor por defecto
    pthread_mutex_init(&mutex_min, NULL); // Inicializa mutex con valor por defecto

    //Crea los threads 
    for(i = 0; i < NUM_THREADS ; i++){
        id_threads[i]=i;
        pthread_create(&threads[i], NULL, buscador_maximos_minimos, &id_threads[i]);
    }
 
    // El hilo llamador espera a que todos terminen de buscar la cantidad de pares en su sub-arreglo.
    for(i = 0; i < NUM_THREADS; i++){
        pthread_join(threads[i], NULL);
    }

    printf("Tiempo en segundos %f\n", dwalltime() - timetick); // Informa el tiempo

    // Verifica el resultado
    if (minimo_global == 0 && maximo_global == (N-1)){
        printf("Resultado correcto\n");
    }else{
        printf("Resultado erroneo\n");
    }

    free(vector);
    pthread_mutex_destroy(&mutex_min);
    pthread_mutex_destroy(&mutex_max);
    return(0);
}