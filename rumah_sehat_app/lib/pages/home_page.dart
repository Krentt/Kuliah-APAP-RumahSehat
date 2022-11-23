import 'package:flutter/material.dart';
import 'package:provider/provider.dart';


class HomePage extends StatefulWidget {
  static const route = "/home";

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text("Rumah Sehat"),
      ),
      body: Text("Halo"),
    );
  }
}