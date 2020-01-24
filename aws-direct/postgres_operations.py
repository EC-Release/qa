import psycopg2
import csv
import time

from datetime import datetime

class Postgres_operations():

    def connectioncheck(self, args):

        print('Inside postgres connectioncheck method')

        before = datetime.now()

        try:
            connection_str = "dbname='"+args.dbname+"' user='"+args.user+"' host='"+args.host+"' password='"+args.password+"' port='"+args.port+"'"

            conn = psycopg2.connect(connection_str)

            cursor = conn.cursor()

            cursor.execute("select name from sct.features where id=5")

            print(cursor.fetchall())

            conn.close()

        except:
            print ("Exception while handling operations")

        after = datetime.now()

        print ('Time to execute: ', after - before)




    def bulkdata(self, args):

        print('Inside postgres bulkdata method')

        before = datetime.now()

        try:
            connection_str = "dbname='"+args.dbname+"' user='"+args.user+"' host='"+args.host+"' password='"+args.password+"' port='"+args.port+"'"

            conn = psycopg2.connect(connection_str)

            cursor = conn.cursor()

            query = "select * from sct.scott_project project, sct.project_savings ps where project.id=ps.project_id"

            cursor.execute(query)

            with open('dbpostgres.csv', 'w') as csvfile:
                csv_writer = csv.writer(csvfile)
                csv_writer.writerows(cursor)

        except:
            print ('Exception while handling postgres operations')

        after = datetime.now()

        print ('Time to execute: ', after - before)



    def activeconnectionforlongtime(self, args):

        print ('Inside postgres activeconnectionforlongtime method')

        before = datetime.now()

        try:
            connection_str = "dbname='"+args.dbname+"' user='"+args.user+"' host='"+args.host+"' password='"+args.password+"' port='"+args.port+"'"

            conn = psycopg2.connect(connection_str)

            cursor = conn.cursor()

            while 1 > 0:

                cursor.execute("select name from sct.features where id=5")

                now = datetime.now()

                print(now.strftime("%H:%M:%S"), cursor.fetchall())

                time.sleep(120)

            cnx.close()

        except:
            print ("Exception while handling postgres database")

        after = datetime.now()

        print ('Time to execute: ', after - before)