import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/add_appotintment_page.dart';
import 'package:rumah_sehat_app/providers/appointment.dart';
import 'package:rumah_sehat_app/widgets/appointments_item.dart';

class ListAppointments extends StatefulWidget {
  static const route = "/list-appt";

  @override
  _ListAppointments createState() => _ListAppointments();
}

class _ListAppointments extends State<ListAppointments> {
  bool isInit = true;
  bool isLoading = false;

  @override
  void didChangeDependencies() {
    if (isInit) {
      isLoading = true;
      Provider.of<Appointments>(context, listen: false).inisialData().then((value) {
        setState(() {
          isLoading = false;
        });
      }).catchError(
            (err) {
          print(err);
          showDialog(
            context: context,
            builder: (context) {
              return AlertDialog(
                title: Text("Error Occured"),
                content: Text(err.toString()),
                actions: [
                  TextButton(
                    onPressed: () {
                      setState(() {
                        isLoading = false;
                      });
                      Navigator.pop(context);
                    },
                    child: Text("Okay"),
                  ),
                ],
              );
            },
          );
        },
      );
    }
    super.didChangeDependencies();
  }

  @override
  Widget build(BuildContext context) {
    final prov = Provider.of<Appointments>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text("Your Appointments"),
        actions: [
          IconButton(
            icon: Icon(Icons.add),
            onPressed: () => Navigator.pushNamed(context, AddAppointmentPage.route),
          ),
        ],
      ),
      body: (isLoading)
          ? Center(
            child: CircularProgressIndicator(),
      )
          : (prov.allAppointments.length == 0)
          ? Center(
            child: Text(
          "No Data",
          style: TextStyle(
            fontSize: 25,
          ),
        ),
      )
          : ListView.builder(
        itemCount: prov.allAppointments.length,
        itemBuilder: (context, i) => AppointmentItem(
          prov.allAppointments[i].kode,
          prov.allAppointments[i].dokter,
          prov.allAppointments[i].isDone,
          prov.allAppointments[i].waktuAwal,
        ),
      ),
    );
  }
}