__author__ = 'Chia Chang <chia.chang@ge.com>'

import psycopg2,time

class PostgresTest(object):
    def __init__(self, constr):
        self.constr=constr
        try:
            self.conn=psycopg2.connect(constr)
        except psycopg2.Error as e:
            self.conn=None
            print(e)
            
    def query(self,qry,raw):
        cursor = self.conn.cursor()
        start_time = time.time()
        cursor.execute(qry)
        elapsed_time = time.time() - start_time
	records = cursor.rowcount
        ref= {'numberOfRecords':records,'message':cursor.statusmessage, 'timeTaken':elapsed_time}
        if raw:
            ref['data']=cursor.fetchall()

        cursor.close()    
        return ref
    
    def insertTestData(self,tname):
        for x in range(0,21):
            ref1=self.query('insert into '+tname+' select * from '+tname+';',False)   
            print(ref1) 
        self.conn.commit()
            
    def initTestTable(self,tname):

        
        ref=self.query("""
CREATE TABLE """+tname+""" (
    code        char(5) ,
    title       varchar(40) NOT NULL,
    did         integer NOT NULL,
    date_prod   date,
    kind        varchar(10),
    len         interval hour to minute
);

insert into """+tname+""" values('abwe','ahsfhakfhskahfsdhfkshfsassa',2,'2016-10-28','ahskfhsf',INTERVAL '82 minute');

insert into """+tname+""" select * from """+tname+""";
        """,False)

        self.conn.commit()
        return ref

    def isTableExists(self,tname):
        ref2=self.query("""
SELECT EXISTS (
   SELECT 1
   FROM   information_schema.tables 
   WHERE  table_schema = 'public'
   AND    table_name = '"""+tname+"""'
   );
        """,True)
        return ref2['data'][0]==(True,)
    
    def initTestData(self,tname):
        if not self.isTableExists(tname):
            ref=self.initTestTable(tname)
            print(ref)
            self.insertTestData(tname)
    
