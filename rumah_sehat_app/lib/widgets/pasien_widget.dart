import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/detail_appointment_page.dart';
import 'package:rumah_sehat_app/providers/appointment.dart';
import 'package:rumah_sehat_app/providers/pasien.dart';

class PasienWidget extends StatelessWidget {
  final String nama, username, email;
  final int saldo, umur;

  const PasienWidget(this.nama, this.username, this.email,
      this.saldo, this.umur, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
      var prov = Provider.of<PasienProvider>(context, listen: false);
      return ListTile(
        onTap: () {
          // Navigator.pushNamed(context, DetailAppointmentPage.route, arguments: kode);
        },
        leading: CircleAvatar(
          child: Padding(
            padding: const EdgeInsets.all(5),
            child: FittedBox(
              child: Text(username, style: TextStyle(fontWeight: FontWeight.bold),),
            ),
          ),
        ),
        title: Text("Name     : $nama \n"
                    "Email    : $email \n"
                    "Umur     : $umur"),
        subtitle: Text("Saldo : $saldo"),
        // trailing: Icon(isDone == true ? Icons.done_all : Icons.timelapse, color: isDone == true? Colors.green : Colors.blueGrey,),
      );
  }
}