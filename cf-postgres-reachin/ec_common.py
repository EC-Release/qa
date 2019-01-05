# -*- coding: utf-8 -*-
__author__ = '安田 アポロ <apolo.yasuda@ge.com>'

import urllib2, zipfile, os,time, fileinput, logging
import subprocess as sp
from time import sleep

class Common(object):
    def __init__(self,className):
        self.initLogger(className)
        
    def Exec(self,cmd):
        ref=sp.Popen(cmd, stdout=sp.PIPE, stderr=sp.PIPE,shell=True)
        stdout, stderr=ref.communicate()    

        if ref.returncode!=0:
            raise RuntimeError("{} failed\n status code:\n {} stdout:\n {} stderr {}".format(cmd, ref.returncode, stdout, stderr))

        print(stdout)

    def Download(self,url):

        while True:
            u = urllib2.urlopen(url)
            meta = u.info()
            file_name = url.split('/')[-1]                
            f = open(file_name, 'wb')
            try:
                file_size = int(meta.getheaders("Content-Length")[0])
                print("Downloading: {} Bytes: {}".format(file_name, file_size))
                file_size_dl = 0
                block_sz = 4096
                while True:
                    buffer = u.read(block_sz)

                    if not buffer:
                        break

                    file_size_dl += len(buffer)
                    f.write(buffer)
                    status = r"%10d  [%3.2f%%]" % (file_size_dl, file_size_dl * 100. / file_size)
                    status = status + chr(8)*(len(status)+1)
                    print(status),

                return f.close()
            
            except:
                print('error downloading file. retry again in 2 sec.')
                sleep(2)
                
                
            f.close()
            
        return exit(1)
        
            
    def Unzip(self,src,dst):
        with zipfile.ZipFile(src, 'r') as zip_ref:
            zip_ref.extractall(dst)
            zip_ref.close()

    def find(self, name, path):
        for root, dirs, files in os.walk(path):
            if name in files or name in dirs:
                print(os.path.join(root, name))
                return os.path.join(root, name)

        raise NameError('no file/directory found.')
    
    def sed(self, _file,text,repl):
        sfile=fileinput.input(files=(_file), inplace=True, backup='.bak')
        for line in sfile:
            print(line.replace(text, repl)),

        sfile.close()

    def initLogger(self,name):
        self.logger = logging.getLogger(name)
        self.logger.setLevel(logging.DEBUG)
        ch = logging.StreamHandler()
        ch.setLevel(logging.DEBUG)
        self.logger.addHandler(ch)
        formatter = logging.Formatter('%(asctime)s|%(name)s|%(levelname)s: %(message)s')
        ch.setFormatter(formatter)
