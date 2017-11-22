class Middleware
   
    def initialize(app)
        @app = app
    end
   
    def call(env)

        status, headers, response = @app.call(env) # en env tengo la que hubiera sido la respuesta del sv
        response_body = response.map { | character | character.gsub(/\d/,'X')} # hago mi modificación a esa respuesta
        [status, headers, response_body] # le envio al cliente la respuesta modificada
    end
end

