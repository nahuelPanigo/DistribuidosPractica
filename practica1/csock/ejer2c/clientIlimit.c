#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 
#include <string.h>
#include <math.h>
#include "crc.h"

void error(char *msg)
{
    perror(msg);
    exit(0);
}

int main(int argc, char *argv[])
{
    int sockfd, portno, n;
    struct sockaddr_in serv_addr;
    struct hostent *server;
    int tamano= pow(10,4);
    char buffer[tamano];

    if (argc < 3) {
       fprintf(stderr,"usage %s hostname port\n", argv[0]);
       exit(0);
    }
	//TOMA EL NUMERO DE PUERTO DE LOS ARGUMENTOS
    portno = atoi(argv[2]);
	
	//CREA EL FILE DESCRIPTOR DEL SOCKET PARA LA CONEXION
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
	//AF_INET - FAMILIA DEL PROTOCOLO - IPV4 PROTOCOLS INTERNET
	//SOCK_STREAM - TIPO DE SOCKET 
	
    if (sockfd < 0) 
        error("ERROR opening socket");
	
	//TOMA LA DIRECCION DEL SERVER DE LOS ARGUMENTOS
    server = gethostbyname(argv[1]);
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host\n");
        exit(0);
    }
    bzero((char *) &serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
	

	//COPIA LA DIRECCION IP Y EL PUERTO DEL SERVIDOR A LA ESTRUCTURA DEL SOCKET
    bcopy((char *)server->h_addr, 
         (char *)&serv_addr.sin_addr.s_addr,
         server->h_length);
     serv_addr.sin_port = htons(portno);
	
	//DESCRIPTOR - DIRECCION - TAMAÑO DIRECCION
    if (connect(sockfd,&serv_addr,sizeof(serv_addr)) < 0) 
        error("ERROR connecting");
    bzero(buffer,tamano);
 
    //ENVIA UN MENSAJE AL SOCKET
    sprintf(buffer, "%d", tamano); 
    n = write(sockfd,buffer,strlen(buffer)); 
    if (n < 0) 
         error("ERROR writing to socket");
    bzero(buffer,tamano);

    printf("The check value for the %s standard is 0x%X\n", CRC_NAME, CHECK_VALUE);
    //llena el buffer con el mensaje

    for(int i=0;i<tamano;i++) buffer[i]='a';
    buffer[tamano]='\0';
    //envia el mensaje
	n = write(sockfd,buffer,strlen(buffer));
    printf("The crcSlow() of buffer is 0x%X\n", crcSlow(buffer, strlen(buffer)));
    if (n < 0) error("ERROR writing to socket");
    bzero(buffer,tamano);
	
    //ESPERA RECIBIR UNA RESPUESTA
	n = read(sockfd,buffer,tamano);
    if (n < 0) error("ERROR reading from socket");
    
	
    return 0;
}
