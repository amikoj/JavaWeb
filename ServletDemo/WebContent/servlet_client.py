#!/usr/bin/env python
# -*-encoding:utf-8 -*-
import  socket


IP_ADDRESS="127.0.0.1"
IP_PORT=8080


def main(data):
    '''
     default test method to Servlet.such as use xmpp protocol to request .
    :return:
    '''

    socket_client=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    socket_client.connect((IP_ADDRESS,IP_PORT))
    socket_client.send(data)
    response=socket_client.recv(1024)
    socket_client.close()
    print "get response %s" % response



def doHttp(method,url):
    data='''%s /ServletDemo/%s HTTP/1.1
Host: %s:%s
Connection: keep-alive
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8

this is a test about %s
''' % (method,url,IP_ADDRESS,IP_PORT,method)
    return data



if __name__ == '__main__':
    print "begin socket conneted."
    main(doHttp("GET","one"))
    # main(doHttp("POST","one"))








