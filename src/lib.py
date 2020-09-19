import psycopg2

from settings import HOST, DB_USER, PASSWORD, DBNAME, PORT, SERVER_PORT

def connect_db():

    try:
        conn = psycopg2.connect(
            user = DB_USER,
            password = PASSWORD,
            host = HOST,
            port = PORT,
            dbname = DBNAME)
    except:
        print("Failed to connect to the database")
    
    return conn

def insert_book(code, name):

    conn = connect_db()

    cursor = conn.cursor()

    query = "INSERT INTO livros (codigo, titulo) VALUES ("+str(code)+",'"+name+"');" 

    cursor.execute(query)
	
    conn.commit()
    count = cursor.rowcount
    
    if(conn):
        cursor.close()
        conn.close()
    
    if (count > 0):
        return str(count)+" registro adicionado com sucesso"
    else:
        return "Erro ao adicionar o registro"

def delete_book(name):

    conn = connect_db()

    cursor = conn.cursor() 

    query = "DELETE FROM livros WHERE TITULO = '"+name+"';"

    cursor.execute(query)

    conn.commit()
    count = cursor.rowcount 

    if(conn):
        cursor.close()
        conn.close()

    if (count > 0):
        return str(count)+" registro removido com sucesso"
    else:
        return "Erro ao remover o registro"

def find_book(name):

    conn = connect_db()

    cursor = conn.cursor()

    query = "SELECT codigo, titulo FROM livros where titulo like '"+name+"';"

    cursor.execute(query)
    records = cursor.fetchall()

    str_list = ''
    count = cursor.rowcount

    if(conn):
        cursor.close()
        conn.close()

    str_list = "CODE  -  NAME\n"
    for row in records:
        str_list += ""+str(row[0])+" - "+row[1]+" \n"
    
    if (count == 0):
        str_list = "No book found!"

    return str_list

def list_books_by_author(name):

    conn = connect_db()

    cursor = conn.cursor()

    query = "SELECT  L.codigo,  L.titulo FROM livros L INNER JOIN livroautor LA on LA.codigolivro = L.codigo INNER JOIN autor A on LA.codigoautor = A.codigo WHERE A.nome like '"+name+"';"

    cursor.execute(query)
    records = cursor.fetchall()

    str_list = ''
    count = cursor.rowcount

    if(conn):
        cursor.close()
        conn.close()

    str_list = "CODE  -  NAME\n"
    for row in records:
        str_list += ""+str(row[0])+" - "+row[1]+" \n"
    
    if (count == 0):
        str_list = "No book found!"

    return str_list

def list_books_per_year_edition(year, edition):

    conn = connect_db()

    cursor = conn.cursor()

    query = "SELECT L.codigo,  L.titulo FROM livros L INNER JOIN edicao E ON E.codigolivro = L.codigo WHERE E.ano = "+str(year)+" AND E.numero like '"+edition+"';"

    cursor.execute(query)
    records = cursor.fetchall()

    str_list = ''
    count = cursor.rowcount

    if(conn):
        cursor.close()
        conn.close()

    str_list = "CODE  -  NAME\n"
    for row in records:
        str_list += ""+str(row[0])+" - "+row[1]+" \n"
    
    if (count == 0):
        str_list = "No book found!"

    return str_list

def update_book(old_name, new_name, year, edition):

    conn = connect_db()

    cursor = conn.cursor()

    query = "UPDATE livros AS L SET titulo = '"+new_name+"' FROM edicao AS E WHERE E.codigolivro = L.codigo AND L.titulo LIKE '"+old_name+"' AND E.numero = '"+edition+"' AND E.ano = "+str(year)+";"

    cursor.execute(query)
    conn.commit()

    count = cursor.rowcount

    if(count > 0):
        return ""+str(count)+" registro atualizado"
    else:
        return "Nenhum registro encontrado"
