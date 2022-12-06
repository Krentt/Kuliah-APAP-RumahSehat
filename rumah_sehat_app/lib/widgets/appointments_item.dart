import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/providers/appointment.dart';

class AppointmentItem extends StatelessWidget {
  final String? kode, dokter;
  final bool? isDone;
  final DateTime? waktuAppointment;

  AppointmentItem(this.kode, this.dokter, this.isDone, this.waktuAppointment);

  @override
  Widget build(BuildContext context) {
    var prov = Provider.of<Appointments>(context, listen: false);
    String date = DateFormat.yMMMd().add_Hm().format(waktuAppointment!);
    return ListTile(
      // onTap: () {
      //   Navigator.pushNamed(context, , arguments: id);
      // },
      leading: CircleAvatar(
        child: Padding(
          padding: const EdgeInsets.all(5),
          child: FittedBox(
            child: Text("$kode", style: TextStyle(fontWeight: FontWeight.bold),),
          ),
        ),
      ),
      title: Text("dr. $dokter"),
      subtitle: Text("Waktu Appointment : $date"),
      trailing: Icon(isDone == true ? Icons.done_all : Icons.timelapse, color: isDone == true? Colors.green : Colors.blueGrey,),
    );
  }
}