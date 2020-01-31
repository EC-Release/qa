import argparse

from mysql_operations import Mysql_operations
from postgres_operations import Postgres_operations


def calloperation(instance, args):
    args.operation.split(',')
    if (args.operation == 'bulkdata'):
        instance.bulkdata(args)
    elif (args.operation == 'longrun'):
        instance.activeconnectionforlongtime(args)
    elif (args.operation == 'conncheck'):
        instance.connectioncheck(args)
    else:
        print ('Please select appropriate operation')



def main():
    print (__name__)

    parser = argparse.ArgumentParser(description="Connecting to mysql database")
    parser.add_argument("--database", default=1, help="mysql/postgres")
    parser.add_argument("--operation", default=2, help="bulkdata/longrun/conncheck")
    parser.add_argument("--host", default=3, help="Host name where database is running")
    parser.add_argument("--port", default=4, help="Port number to connect to database")
    parser.add_argument("--user", default=5, help="Database user name")
    parser.add_argument("--password", default=6, help="Database password")
    parser.add_argument("--dbname", default=7, help="Database or schema name")

    args = parser.parse_args()

    if (args.database == 'mysql'):
        print ('Connecting to mysql database')

        m = Mysql_operations()

        calloperation(m, args)

    elif (args.database == 'postgres'):
        print ('Connecting to postgres database')

        p = Postgres_operations()

        calloperation(p, args)

    else:
        print ('Please select appropriate database')
        exit(0)




if __name__ == '__main__':
    main()


# Execute below command to run the program

# Mysql
# python db_main.py --database mysql --operation conncheck --host localhost --port 3306 --user root --password {dbpassword} --dbname dpdt
# python db_main.py --database mysql --host {hostip} --port 7979 --user root --password {dbpassword} --dbname dpdt

# Postgres
# python db_main.py --database postgres --operation conncheck --host localhost --port 5432 --user postgres --password postgres --dbname {databasename}
# python db_main.py --database postgres --operation bulkdata --host {hostip} --port 7978 --user postgres --password postgres --dbname {databasename}

# Execute below command to get help and allowed values for arguments
# python db_main.py -h

# Prerequisites to install
# pip install mysql-connector-python
# sudo yum install gcc python-setuptools python-devel postgresql-devel
# pip install psycopg2

# -- Working with python 3
# virtualenv --python=/Library/Frameworks/Python.framework/Versions/3.6/bin/python3 venvs/postgrestest
# source ~/venvs/postgrestest/bin/activate
# pip install psycopg2
# pip install mysql-connector-python
