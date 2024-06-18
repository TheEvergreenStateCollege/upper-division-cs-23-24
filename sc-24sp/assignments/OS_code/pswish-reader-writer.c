#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>
#include <pthread.h>

#include "common_threads.h"

//
// Your code goes in the structure and functions below
//

typedef struct __rwlock_t
{
    sem_t wrt;
    sem_t mutex;
    sem_t noReaders;
    sem_t writers_waiting;
    int readCount;
} rwlock_t;

void rwlock_init(rwlock_t *rw)
{
    rw->readCount = 0;
    sem_init(&rw->wrt, 0, 1);
    sem_init(&rw->mutex, 0, 1);
    sem_init(&rw->noReaders, 0, 1);
    sem_init(&rw->writers_waiting, 0, 1);
}

void rwlock_acquire_readlock(rwlock_t *rw)
{
    // Starvation prevention
    sem_wait(&rw->writers_waiting);

    sem_wait(&rw->mutex);
    rw->readCount++;
    if (rw->readCount == 1)
        sem_wait(&rw->noReaders);
    // simulate startvation
    // sleep(1);
    sem_post(&rw->mutex);

    // Starvation prevention
    sem_post(&rw->writers_waiting);
}

void rwlock_release_readlock(rwlock_t *rw)
{

    sem_wait(&rw->mutex);
    rw->readCount--;
    if (rw->readCount == 0)
    {
        sem_post(&rw->noReaders);
    }
    sem_post(&rw->mutex);
}

void rwlock_acquire_writelock(rwlock_t *rw)
{
    // Starvation prevention
    sem_wait(&rw->writers_waiting);
    sem_wait(&rw->wrt);
    // sleep(1);
    sem_wait(&rw->noReaders);

    // Starvation prevention
    sem_post(&rw->writers_waiting);
}

void rwlock_release_writelock(rwlock_t *rw)
{
    sem_post(&rw->wrt);
    sem_post(&rw->noReaders);
    // Starvation prevention
    sem_post(&rw->writers_waiting);
}

//
// Don't change the code below (just use it!)
//

int loops;
int value = 0;

rwlock_t lock;

void *reader(void *arg)
{
    int i;
    for (i = 0; i < loops; i++)
    {
        rwlock_acquire_readlock(&lock);
        printf("read %d\n", value);
        rwlock_release_readlock(&lock);
    }
    return NULL;
}

void *writer(void *arg)
{
    int i;
    for (i = 0; i < loops; i++)
    {
        rwlock_acquire_writelock(&lock);
        value++;
        printf("write %d\n", value);
        rwlock_release_writelock(&lock);
    }
    return NULL;
}

int main(int argc, char *argv[])
{
    assert(argc == 4);
    int num_readers = atoi(argv[1]);
    int num_writers = atoi(argv[2]);
    loops = atoi(argv[3]);

    pthread_t pr[num_readers], pw[num_writers];

    rwlock_init(&lock);

    printf("begin\n");

    int i;
    for (i = 0; i < num_readers; i++)
        Pthread_create(&pr[i], NULL, reader, NULL);
    for (i = 0; i < num_writers; i++)
        Pthread_create(&pw[i], NULL, writer, NULL);

    for (i = 0; i < num_readers; i++)
        Pthread_join(pr[i], NULL);
    for (i = 0; i < num_writers; i++)
        Pthread_join(pw[i], NULL);

    printf("end: value %d\n", value);

    return 0;
}
