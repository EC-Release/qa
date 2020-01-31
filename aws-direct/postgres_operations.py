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

            afterqueryexecute = datetime.now()

            print('Time to execute query: ', afterqueryexecute - before)

            print(cursor.fetchall())

            conn.close()

        except Exception, e:
            print ("Exception while handling the operations")
            print(e)

        after = datetime.now()

        print ('Total time to execute: ', after - before)




    def bulkdata(self, args):

        print('Inside postgres bulkdata method')
        before = datetime.now()
        try:
            connection_str = "dbname='"+args.dbname+"' user='"+args.user+"' host='"+args.host+"' password='"+args.password+"' port='"+args.port+"'"

            conn = psycopg2.connect(connection_str)

            cursor = conn.cursor()

            query = "select * from sct.scott_project project, sct.project_savings ps where project.id=ps.project_id"

            cursor.execute(query)

            afterqueryexec = datetime.now()
            print("Query execution completed: ", afterqueryexec - before)

            with open('dbpostgres.csv', 'w') as csvfile:
                csv_writer = csv.writer(csvfile)
                csv_writer.writerows(cursor)

            conn.close()

        except Exception, e:
            print ('Exception while handling postgres operations')
            print(e)

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

                time.sleep(1800)

            cnx.close()

        except Exception, e:
            print ("Exception while handling postgres database")
            print(e)

        after = datetime.now()

        print ('Time to execute: ', after - before)