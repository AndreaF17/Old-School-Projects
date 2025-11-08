#include <iostream>
#include <string>
#include <sstream>
using namespace std;

#define N 10
#define GRADES 5
#define STUDENTS 5

typedef struct studente{
    string nome;
    string cognome;
    int media;
} studente;

studente studente[STUDENTS]={
    {
      .nome="Marco",
      .cognome="Rossi",
      .media=8,
    },
    {
      .nome="Pino",
      .cognome="Basso",
      .media=6,
    },
    {
    .nome="Giorgio",
    .cognome="Giovanni",
    .media=4,
    },
    {
    .nome="Andre",
    .cognome="Rossi",
    .media=10
    },
    {
      .nome="Mario",
      .cognome="Rossi",
      .media=2,
    }
  };

int main(int argc, const char *argv[]){
    int MAX=0;
    for (int i = 0; i < STUDENTS; i++)
    {
        if(studente[i].media>MAX){
            studente[i].media=MAX;
        }
    }
    printf("L'alunno/i con voto piu' alto/i e'/sono: /n");

    for (int i = 0; i < STUDENTS; i++){
        if(studente[i].media==MAX){
            printf("Nome: %s/n Cognome: %s /n Voto: %d/n",studente[i].nome, studente[i].cognome, studente[i].media);
        }

    }

        return 0;
}
