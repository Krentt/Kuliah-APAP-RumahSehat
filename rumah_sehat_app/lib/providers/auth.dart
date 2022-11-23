import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class Authh with ChangeNotifier{
  void signup(String? username, String? password, String? email, String? umur) async{
    Uri url = Uri.parse("http://10.0.2.2:8080/user/pasien-signup");
    var response = await http.post(url, body: json.encode({
      "username": username,
      "password": password,
      "email": email,
      "umurPasien" : umur
    }));
    print(json.decode(response.body));
  }
}