cut -d: -f1,3 /etc/passwd | egrep ':[0-9]{4}$' | cut -d: -f1

# cut -d: indica por que campos queremos cortar
# -f1,3 indica que queremos cortar la fila 1 (nombre de usuario) y 3 (uid)
# /etc/passwd es donde están los usuarios del sistema
# egrep ':[0-9]{4}$' nos indica que queremos resultados con 4 caracteres.
# El último cut nos indica que queremos solo el nombre de usuario.