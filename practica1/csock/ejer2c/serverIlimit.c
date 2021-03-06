/* A simple server in the internet domain using TCP
   The port number is passed as an argument */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <math.h>
#include "crc.h"

#define  MAXSIZEBUF 32767


void error(char *msg)
{
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[])
{
    int sockfd, newsockfd, portno, clilen;
    struct sockaddr_in serv_addr, cli_addr;
    int n;
    long int tamano= pow(10,4);
    char buffer[tamano];
    char *buffer2;

    buffer2= malloc(tamano*sizeof(char));
    if (argc < 2) {
         fprintf(stderr,"ERROR, no port provided\n");
         exit(1);
     }
	//CREA EL FILE DESCRIPTOR DEL SOCKET PARA LA CONEXION
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
	//AF_INET - FAMILIA DEL PROTOCOLO - IPV4 PROTOCOLS INTERNET
	//SOCK_STREAM - TIPO DE SOCKET 
	
    if (sockfd < 0) error("ERROR opening socket");
    bzero((char *) &serv_addr, sizeof(serv_addr));
     //ASIGNA EL PUERTO PASADO POR ARGUMENTO
	 //ASIGNA LA IP EN DONDE ESCUCHA (SU PROPIA IP)
	portno = atoi(argv[1]);
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(portno);
	 
	 //VINCULA EL FILE DESCRIPTOR CON LA DIRECCION Y EL PUERTO
    if (bind(sockfd, (struct sockaddr *) &serv_addr,
              sizeof(serv_addr)) < 0) 
              error("ERROR on binding");
			  
	//SETEA LA CANTIDAD QUE PUEDEN ESPERAR MIENTRAS SE MANEJA UNA CONEXION		  
    listen(sockfd,5);
	 
	 // SE BLOQUEA A ESPERAR UNA CONEXION
    clilen = sizeof(cli_addr);
    newsockfd = accept(sockfd, 
                 (struct sockaddr *) &cli_addr, 
                 &clilen);
				 
     //DEVUELVE UN NUEVO DESCRIPTOR POR EL CUAL SE VAN A REALIZAR LAS COMUNICACIONES
	if (newsockfd < 0)  error("ERROR on accept");
    bzero(buffer,tamano);

	//LEE EL MENSAJE DEL CLIENTE
    n = read(newsockfd,buffer,tamano);
    int num=atoi(buffer);
    if (n < 0) error("ERROR reading from socket");

    bzero(buffer,tamano);
    int top= num/MAXSIZEBUF;
  
    for(int i=0;i<=top;i++){
        n = read(newsockfd,buffer,MAXSIZEBUF);
        if (n < 0) error("ERROR reading from socket");
        else buffer2=strcat(buffer2,buffer);
        bzero(buffer,tamano);
    }

    buffer2[tamano]='\0';
    crcInit();
    printf("The crcFast() of buffer is 0x%X\n", crcFast(buffer2, strlen(buffer2)));
    printf("Here is the message: %s\n",buffer2);

    //RESPONDE AL CLIENTE
    n = write(newsockfd,"I got your message",18);
    if (n < 0) error("ERROR writing to socket");
    return 0; 
}
