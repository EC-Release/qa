import time
from datetime import datetime

import mysql.connector


class Mysql_operations():

    def connectioncheck(self, args):

        print('Inside mysql connectioncheck method')

        before = datetime.now()

        try:
            cnx = mysql.connector.connect(host=args.host, port=args.port, user=args.user, password=args.password,
                                          database=args.dbname)

            cur = cnx.cursor(buffered=True)

            cur.execute("show tables")

            print(cur.fetchall())

            cnx.close()

        except mysql.connector.Error as err:
            print ("Error number: ", err.errno, ", Error Message: ", err.message)

        after = datetime.now()

        print ('Time difference: ', after - before)




    def bulkdata(self, args):

        print('Mysql bulkdata method yet to implement...')



    def activeconnectionforlongtime(self, args):

        print ('Inside mysql activeconnectionforlongtime method')

        try:
            cnx = mysql.connector.connect(host=args.host, port=args.port, user=args.user, password=args.password,
                                          database=args.dbname)

            cur = cnx.cursor(buffered=True)

            while 1 > 0:

                cur.execute("select 1 from dual")

                now = datetime.now()

                print(now.strftime("%H:%M:%S"), cur.fetchall())

                time.sleep(120)

            cnx.close()

        except mysql.connector.Error as err:
            print ("Error number: ", err.errno, ", Error Message: ", err.message)

