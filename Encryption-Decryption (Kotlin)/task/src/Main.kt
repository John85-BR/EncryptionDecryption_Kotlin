package encryptdecrypt

import java.io.File

fun argument(alg:String, op: String, message: String, key: Int): String{

    var string = ""
    if(alg=="unicode"){
        when(op){
            "enc" -> for(i in message) string+=Char(i.code + key)
            "dec" -> for(i in message) string+=Char(i.code - key)
        }
    } else if(alg=="shift"){
        when(op){
            "enc" -> {
                for(i in message) {

                    if(i in 'a'..'z'){
                        if(((i.code + key - 97)%26 + 97)<97){
                            string += Char(i.code + 26 - key)
                        }else{
                            string+=Char((i.code + key - 97)%26 + 97)
                        }

                    }else if(i in 'A'..'Z'){
                        if(((i.code + key - 65)%26 + 65)<65){
                            string += Char(i.code + 26 - key)
                        }else{
                            string+=Char((i.code + key - 65)%26 + 65)
                        }
                    } else{
                        string+=i
                    }
                }
            }
            "dec" -> {
                for(i in message) {
                    if(i in 'a'..'z'){
                        if(((i.code - key - 97)%26 + 97)<97){
                            string += Char(i.code + 26 - key)
                        }else{
                            string+=Char((i.code - key - 97)%26 + 97)
                        }

                    }else if(i in 'A'..'Z'){
                        if(((i.code - key - 65)%26 + 65)<65){
                            string += Char(i.code + 26 - key)
                        }else{
                            string+=Char((i.code - key - 65)%26 + 65)
                        }

                    } else{
                        string+=i
                    }
                }
            }
        }
    }

    //str[i].code + key - 97) % 26 + 97
    return string

    /*for(i in message){
        if(i in 'a'..'z'){
            print(Char((i.code + key - 97)%26 + 97))
        } else print(i)
    }*/

}

fun encryption(message: String, key: Int, _in: String, out: String, alg: String){

    var string = ""
    if(_in!=""){
        if(message!=""){
            if(out!=""){
                string+=argument(alg, "enc", message, key)
                File(out).writeText(string)
            }else{
                print(argument(alg, "enc", message, key))
            }
        }else{
            if(File(_in).exists()) { // checks if file exists
                val temp = File(_in).readText()
                string+=argument(alg, "enc", temp, key)
                File(out).writeText(string)
            } else print("Error")
        }
    }else if(message!=""){
        if(out!=""){
            string+=argument(alg, "enc", message, key)
            File(out).writeText(string)
        }else{
            print(argument(alg, "enc", message, key))
        }
    }else{
        print("Error")
    }

}

fun decryption(message: String, key: Int, _in: String, out: String, alg: String){

    var string = ""
    if(_in!=""){
        if(message!=""){
            if(out!=""){
                for(i in message) string+=Char(i.code - key)
                File(out).writeText(string)
            }else{
                print(argument(alg, "dec", message, key))
            }
        }else{
            if (File(_in).exists()) { // checks if file exists
                val temp = File(_in).readText()
                string+=argument(alg, "dec", temp, key)
                File(out).writeText(string)
            } else print("Error")
        }
    }else if(message!=""){
        if(out!=""){
            string+=argument(alg, "dec", message, key)
            File(out).writeText(string)
        }else{
            print(argument(alg, "dec", message, key))
        }
    }else{
        print("Error")
    }
}

fun main(args: Array<String>) {

    var op = "enc"
    var message = ""
    var key = 5
    var in_ = ""
    var out = ""
    var alg = "shift"

    for(i in args.indices){
        if(args[i]=="-mode") op = args[i+1]

        if(args[i]=="-key") key = args[i+1].toInt()

        if(args[i]=="-data") message = args[i+1]

        if(args[i]=="-in") in_ = args[i+1]

        if(args[i]=="-out") out = args[i+1]

        if(args[i]=="-alg") alg = args[i+1]
    }


    /*var encrypt = ""
    for(i in message) encrypt += if(i in 'a'..'z') 'z' - (i - 'a') else i
    for(i in encrypt) print(if(i in 'a'..'z') '#' else i)*/
    //str[i].code + key - 97) % 26 + 97
    //print(message.map{ if(it in 'a'..'z') 'z'.minus((it.minus('a'))) else it }.joinToString("").lowercase())



    when(op){
        "dec" -> decryption(message, key, in_, out, alg)
        "enc" -> encryption(message, key, in_, out, alg)
    }

}


