import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/providers/dokters.dart';

import '../providers/appointment.dart';

class AddAppointmentPage extends StatefulWidget {
  static const route = "/add-appointment";

  @override
  State<AddAppointmentPage> createState() => _AddAppointmentPageState();
}

class _AddAppointmentPageState extends State<AddAppointmentPage> {
  final TextEditingController tanggalController = TextEditingController();
  String? selectedDokter;
  DateTime? waktuAppointment;

  bool isInit = true;
  bool isLoading = false;
  @override
  void didChangeDependencies(){
    if (isInit) {
      isLoading = true;
      isInit = false;
      Provider.of<Dokters>(context, listen: false).inisialData().then((value) {
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
    final prov = Provider.of<Dokters>(context);

    List<DropdownMenuItem<String>> dropDownDokter = [];
    // Add list dokter to dropdownmenu item, set value nya jadi username dokter
    for (int i = 0; i < prov.allDokter.length; i++){
      String? nama = "";
      String? tarif = "";
      nama = prov.allDokter[i].nama;
      tarif = prov.allDokter[i].tarif.toString();
      String? item = nama! + " - " + tarif;
      var newItem = DropdownMenuItem(child: Text(item), value: prov.allDokter[i].username,);
      dropDownDokter.add(newItem);
    }


    void save(String dokter, DateTime waktuAwal) {
        Provider.of<Appointments>(context, listen: false)
            .addAppointment(dokter, waktuAwal)
            .then((value) => Navigator.pop(context)).catchError((err){
          showDialog(
            context: context,
            builder: (context) {
              return AlertDialog(
                title: Text("Error Occured"),
                content: Text("Error : $err"),
                actions: [
                  TextButton(
                    onPressed: () => Navigator.pop(context),
                    child: Text("OKAY"),
                  ),
                ],
              );
            },
          );
        });
    }

    return Scaffold(
      appBar: AppBar(
        title: Text("Add Appointment"),
        actions: [
          IconButton(
            icon: Icon(Icons.save),
            onPressed: () => save(selectedDokter!, waktuAppointment!),
          ),
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Column(
              children: [
                DropdownButtonFormField<String>(
                  items: dropDownDokter,
                  value: selectedDokter,
                  onChanged: (value) {
                    setState(() {
                      selectedDokter = value;
                    });
                  },
                  decoration: InputDecoration(
                    labelText: "Dokter",
                    prefixIcon: Icon(Icons.man),
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                  ),
                ),
                SizedBox(height: 20),
                TextField(
                  autocorrect: false,
                  autofocus: true,
                  controller: tanggalController,
                  textInputAction: TextInputAction.next,
                  decoration: InputDecoration(
                    labelText: "Tanggal Appointment",
                    prefixIcon: Icon(Icons.calendar_month),
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                  ),
                    onTap: () {
                      showDatePicker(
                          context: context,
                          initialDate: DateTime.now(),
                          firstDate: DateTime(2000),
                          lastDate: DateTime(2025)
                      ).then((date) {
                        if (date != null){
                          showTimePicker(context: context,
                              initialTime: TimeOfDay.now()).then((time){
                                setState(() {
                                  if (time != null){
                                    waktuAppointment = DateTime(
                                        date.year,
                                        date.month,
                                        date.day,
                                    time.hour,
                                    time.minute);
                                    tanggalController.text = waktuAppointment.toString().substring(0,16);
                                  }
                                });
                          });
                        }
                      });
                    }
                ),
              ],
            ),
            Container(
              width: double.infinity,
              margin: EdgeInsets.only(bottom: 30),
              child: ElevatedButton(
                onPressed: () =>
                    save(selectedDokter!, waktuAppointment!),
                child: Text(
                  "Save",
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
              ),
            ),
          ],
        ),
      ),
    );
  }
}