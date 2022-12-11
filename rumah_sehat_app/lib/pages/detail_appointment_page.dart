import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/detail_resep_page.dart';
import 'package:rumah_sehat_app/providers/appointment.dart';

class DetailAppointmentPage extends StatefulWidget {
  static const route = "/detail-appointment";

  @override
  State<DetailAppointmentPage> createState() => _DetailAppointmentPageState();
}

class _DetailAppointmentPageState extends State<DetailAppointmentPage> {
  @override
  Widget build(BuildContext context) {
    String kodeAppt = ModalRoute.of(context)?.settings.arguments as String;
    var prov = Provider.of<Appointments>(context, listen: false);
    var selectedAppointment = prov.selectByKode(kodeAppt);

    return Scaffold(
      appBar: AppBar(
        title: Text("Detail Appointment"),
        elevation: 0.0,
        backgroundColor: Colors.blue,
        centerTitle: true,
        leading: GestureDetector(
          onTap: () {
            Navigator.pop(context);
          },
          child: Icon(
            Icons.arrow_back,
            color: Colors.white,
          ),
        ),
      ),
      body: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              height: 200,
              decoration: BoxDecoration(
                  color: Colors.blue,
                  borderRadius: BorderRadius.only(bottomLeft: Radius.circular(30), bottomRight: Radius.circular(30))
              ),
              child: Container(
                margin: EdgeInsets.only(bottom: 20),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Container(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Container(
                            margin: EdgeInsets.only(top: 20),
                            child: Text("Kode",
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20),
                            child: Text("Dokter",
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20),
                            child: Text("Waktu",
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20),
                            child: Text("Status",
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    Container(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Container(
                            margin: EdgeInsets.only(top: 20, left: 40),
                            child: Text(selectedAppointment.kode.toString(),
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 22,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20, left: 40),
                            child: Text(selectedAppointment.dokter.toString().toUpperCase(),
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20, left: 40),
                            child: Text(DateFormat("dd-MM-yyyy HH:mm").format(selectedAppointment.waktuAwal as DateTime),
                              style: TextStyle(
                                color: Colors.yellow,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20, left: 40),
                            child: Text(selectedAppointment.isDone == true? "SELESAI" : "BELUM SELESAI" ,
                              style: TextStyle(
                                color: selectedAppointment.isDone == true? Colors.lightGreen : Colors.amber,
                                fontSize: 20,
                                fontFamily: 'Roboto',
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                        ],
                      ),
                    )
                  ],
                ),
              ),
            ),
            Container(
              margin: EdgeInsets.only(left: 20, top: 30),
              child: Text('Deskripsi',
                style: TextStyle(
                  color: Color(0xff363636),
                  fontSize: 25,
                  fontFamily: 'Roboto',
                  fontWeight: FontWeight.w700,
                ),
              ),
            ),
            Container(
              margin: EdgeInsets.only(left: 20, top: 10, right: 20),
              child: Text('Appointment merupakan sesi konsultasi Anda '
                  ' dengan dokter sehingga dimohon datang tepat waktu. '
                  'Sesi konsultasi ini kurang lebih akan berlangsung '
                  'selama satu jam.',
                style: TextStyle(
                  color: Color(0xff363636),
                  fontSize: 18,
                  fontFamily: 'Roboto',
                ),
                textAlign: TextAlign.justify,
              ),
            ),
            SizedBox(height: 20),
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(10),
              margin: const EdgeInsets.only(bottom: 30),
              child: selectedAppointment.kodeResep != null ? ElevatedButton(
                onPressed: () {
                  Navigator.pushNamed(context, DetailResepPage.route, arguments: selectedAppointment.kodeResep);
                },
                child: Text(
                  "Detail Resep",
                  style: TextStyle(
                    fontSize: 18,
                  ),
                ),
                style: ElevatedButton.styleFrom(
                  padding: EdgeInsets.symmetric(vertical: 15),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(100),
                  ),
                ),
              ) : Text("TIDAK ADA RESEP",
              style: TextStyle(
                color: Colors.blue,
                fontSize:20,
                fontFamily: 'Roboto',
                fontWeight: FontWeight.bold
              ),
                textAlign: TextAlign.center,
              ),
            ),
          ],
        ),
    );
  }
}