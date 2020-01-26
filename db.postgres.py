# -*- coding: utf-8 -*-
__author__ = '安田 アポロ <apolo.yasuda@ge.com>'

import atexit

def main():
    atexit.register(exit_handler)
    print('done test')
        
def exit_handler():
    cleanup()
    os.system('error')
        
if __name__=='__main__':
    main()
