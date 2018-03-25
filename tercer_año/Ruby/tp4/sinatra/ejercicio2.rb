require 'rack'

class MoL
    def self.call(env)
        num = rand(42)
        
        num == 42 ? code = 200 : code = 404

        [code, {}, [num.to_s]]
    end
end

  
Rack::Server.start :app => MoL
