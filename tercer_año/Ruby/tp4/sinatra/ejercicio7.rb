class Middleware2
    
    def initialize(app)
        @app = app
    end

    def call(env)

        status, headers, response = @app.call(env) # en env tengo la que hubiera sido la respuesta del sv
        headers['X-Xs-Count'] = response.to_s.count('X').to_s
        [status, headers, response] # le envio al cliente la respuesta modificada
    end
end