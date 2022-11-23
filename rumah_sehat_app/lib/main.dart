import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/auth_page.dart';
import 'providers/auth.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return  MultiProvider(providers: [
      ChangeNotifierProvider(create: (ctx) => Authh(),),
    ],
        builder: (context, child) => MaterialApp(
          debugShowCheckedModeBanner: false,
          home: LoginScreen(),
        ),
    );
  }
}
