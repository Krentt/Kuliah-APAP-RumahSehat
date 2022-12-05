import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:rumah_sehat_app/pages/add_appotintment_page.dart';
import 'package:rumah_sehat_app/widgets/main_drawer.dart';
import 'package:carousel_slider/carousel_slider.dart';

class HomePage extends StatefulWidget {
  static const route = "/home";

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final List<String> imgList = [
    'https://images.unsplash.com/photo-1666214276372-24e331683e78?ixlib=rb-4.0.3&ixid=MnwxMjA3fDF8MHxzZWFyY2h8MXx8bWVkaWNhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=1000&q=60',
    'https://images.unsplash.com/photo-1586773860418-d37222d8fce3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGhvc3BpdGFsfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=1000&q=60',
    'https://images.unsplash.com/photo-1526256262350-7da7584cf5eb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8bWVkaWNhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=1000&q=60',
    'https://images.unsplash.com/photo-1666214280391-c9ef08d09da8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDF8MHxzZWFyY2h8OHx8bWVkaWNhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=1000&q=60',
    'https://images.unsplash.com/photo-1666214275099-0ca566aefe26?ixlib=rb-4.0.3&ixid=MnwxMjA3fDF8MHxzZWFyY2h8MTV8fG1lZGljYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=1000&q=60',
    'https://images.unsplash.com/photo-1585421514738-01798e348b17?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fG1lZGljYWx8ZW58MHx8MHx8&auto=format&fit=crop&w=1000&q=60'
  ];

  @override
  Widget build(BuildContext context) {
    double height = MediaQuery.of(context).size.height;
    double width = MediaQuery.of(context).size.width;
    return Scaffold(
        appBar: AppBar(
          title: const Text("Rumah Sehat"),
        ),
        drawer: DrawerScreen(),
        body: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              Container(
                alignment: Alignment.center,
                child: Card(
                  color: Colors.white70,
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(9)),
                  elevation: 20,
                  margin: const EdgeInsets.all(20),
                  child: InkWell(
                    onTap: () {},
                    splashColor: Colors.blue,
                    child: SizedBox(
                      width: 400,
                      height: 90,
                      child: Row(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Padding(
                            padding: const EdgeInsets.all(11.0),
                            child: Icon(
                              Icons.account_circle,
                              size: 60,
                              color: Colors.black,
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.all(30.0),
                            child: Text(
                              "Pacar soobin Nina",
                              textAlign: TextAlign.left,
                              style: TextStyle(
                                  color: Colors.black,
                                  fontWeight: FontWeight.bold,
                                  fontSize: 22),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              ),
              Container(
                height: 240,
                child: CarouselSlider(
                  options: CarouselOptions(
                    autoPlay: true,
                    aspectRatio: 2.0,
                    enlargeCenterPage: true,
                  ),
                  items: imgList
                      .map((item) => Container(
                            child: Container(
                              margin: EdgeInsets.all(5.0),
                              child: ClipRRect(
                                  borderRadius:
                                      BorderRadius.all(Radius.circular(5.0)),
                                  child: Stack(
                                    children: <Widget>[
                                      Image.network(item,
                                          fit: BoxFit.cover, width: 1000.0),
                                      Positioned(
                                        bottom: 0.0,
                                        left: 0.0,
                                        right: 0.0,
                                        child: Container(
                                          decoration: BoxDecoration(
                                            gradient: LinearGradient(
                                              colors: [
                                                Color.fromARGB(200, 0, 0, 0),
                                                Color.fromARGB(0, 0, 0, 0)
                                              ],
                                              begin: Alignment.bottomCenter,
                                              end: Alignment.topCenter,
                                            ),
                                          ),
                                          padding: EdgeInsets.symmetric(
                                              vertical: 10.0, horizontal: 20.0),
                                        ),
                                      ),
                                    ],
                                  )),
                            ),
                          ))
                      .toList(),
                ),
              ),
              Container(
                  child: Padding(
                padding: EdgeInsets.all(17),
                child: Column(
                  children: <Widget>[
                    Container(
                      child: Row(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          const SizedBox(
                            width: 15,
                          ),
                          Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: <Widget>[
                              SizedBox(height: 15),
                              Text(
                                "Welcome to Rumah Sehat",
                                style: TextStyle(
                                    fontSize: 25, fontWeight: FontWeight.bold),
                              ),
                              SizedBox(height: 15),
                              Text(
                                "Sehat Bersama kami dengan\nkemudahan konsultasi kesehatan\ndalam genggaman",
                                textAlign: TextAlign.justify,
                                style: TextStyle(fontSize: 15),
                              ),
                              SizedBox(height: 25),
                              Text(
                                "Layanan kami",
                                style: TextStyle(fontSize: 20),
                              ),
                            ],
                          )
                        ],
                      ),
                    )
                  ],
                ),
              )),
              GridView.count(
                shrinkWrap: true,
                primary: false,
                padding: const EdgeInsets.all(25),
                crossAxisCount: 2,
                scrollDirection: Axis.vertical,
                mainAxisSpacing: 4,
                crossAxisSpacing: 4,
                children: <Widget>[
                  GestureDetector(
                    onTap: () {},
                    child: Card(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10)),
                      elevation: 20,
                      margin: const EdgeInsets.all(8),
                      child: InkWell(
                        onTap: () =>
                        Navigator.pushNamed(context, AddAppointmentPage.route),
                        splashColor: Colors.blue,
                        child: Center(
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              Icon(
                                Icons.add,
                                size: 90,
                                color: Colors.brown,
                              ),
                              Text(
                                "Create Appointment",
                                softWrap: true,
                                style: TextStyle(fontSize: 17),
                              )
                            ],
                          ),
                        ),
                      ),
                    ),
                  ),
                  GestureDetector(
                    onTap: () {},
                    child: Card(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10)),
                      elevation: 20,
                      margin: const EdgeInsets.all(8),
                      child: InkWell(
                        onTap: () {
                          // TODO : MASUKIN ROUTE
                        },
                        splashColor: Colors.blue,
                        child: Center(
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              Icon(
                                Icons.today,
                                size: 90,
                                color: Colors.green,
                              ),
                              Text(
                                "Appointments",
                                softWrap: true,
                                style: TextStyle(
                                  fontSize: 16,
                                  fontWeight: FontWeight.w400,
                                ),
                              ),
                            ],
                          ),
                        ),
                      ),
                    ),
                  ),
                  GestureDetector(
                    onTap: () {},
                    child: Card(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10)),
                      elevation: 20,
                      margin: const EdgeInsets.all(8),
                      child: InkWell(
                        onTap: () {
                          // TODO : MASUKIN ROUTE
                        },
                        splashColor: Colors.blue,
                        child: Center(
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              Icon(
                                Icons.payment,
                                size: 90,
                                color: Colors.purple,
                              ),
                              Text(
                                "My Bills",
                                softWrap: true,
                                style: TextStyle(
                                  fontSize: 16,
                                  fontWeight: FontWeight.w400,
                                ),
                              ),
                            ],
                          ),
                        ),
                      ),
                    ),
                  )
                ],
              )
            ],
          ),
        ));
  }
}
