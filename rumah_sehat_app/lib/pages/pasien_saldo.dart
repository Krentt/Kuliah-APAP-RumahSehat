import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/providers/pasien.dart';
import 'package:rumah_sehat_app/widgets/pasien_widget.dart';

class PasienSaldo extends StatefulWidget {
  static const route = "/pasien/saldo";

  const PasienSaldo({Key? key}) : super(key: key);

  @override
  _PasienSaldoState createState() => _PasienSaldoState();
}

class _PasienSaldoState extends State<PasienSaldo> {
  bool isInit = true;
  bool isLoading = false;

  /// Selected Saldo for dropdown menu
  int? selectedSaldo;
  String? selectedSaldoStr;
  final TextEditingController saldoController = TextEditingController();


  @override
  void didChangeDependencies() {
    if (isInit) {
      isLoading = true;
      Provider.of<PasienProvider>(context, listen: false).getPasienProfile().then((
          value) {
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
                title: const Text("Error Occured"),
                content: Text(err.toString()),
                actions: [
                  TextButton(
                    onPressed: () {
                      setState(() {
                        isLoading = false;
                      });
                      Navigator.pop(context);
                    },
                    child: const Text("Okay"),
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

  /// Flutter Page Starts Here
  @override
  Widget build(BuildContext context) {
    final prov = Provider.of<PasienProvider>(context);

    /// Drop down menu Choices
    List<DropdownMenuItem<String>> pilihanSaldo = [];
    var newSaldo = const DropdownMenuItem(child: Text("Rp. 20.000"), value:"20000", );
    pilihanSaldo.add(newSaldo);
    newSaldo = const DropdownMenuItem(child: Text("Rp. 50.000"), value:"50000", );
    pilihanSaldo.add(newSaldo);
    newSaldo = const DropdownMenuItem(child: Text("Rp. 100.000"), value:"100000", );
    pilihanSaldo.add(newSaldo);
    newSaldo = const DropdownMenuItem(child: Text("Rp. 250.000"), value:"250000", );
    pilihanSaldo.add(newSaldo);
    newSaldo = const DropdownMenuItem(child: Text("Rp. 500.000"), value:"500000", );
    pilihanSaldo.add(newSaldo);
    newSaldo = const DropdownMenuItem(child: Text("Rp. 1.000.000"), value:"1000000", );
    pilihanSaldo.add(newSaldo);


    /// TODO : Save Function to Update Saldo
    void save(int saldo) {
      // print("Saldoselected : " + saldo.toString());
      Provider.of<PasienProvider>(context, listen: false)
          .isiSaldo(saldo)
          .then((value) => Navigator.pop(context)).catchError((err){
        showDialog(
          context: context,
          builder: (context) {
            return AlertDialog(
              title: const Text("Error Occured"),
              content: Text("Error : $err"),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: const Text("OKAY"),
                ),
              ],
            );
          },
        );
      });
    }

    return Scaffold(
      /// Application Header (AppBar)
      appBar: AppBar(
        title: const Text("Isi Saldo"),
      ),

      /// Application Body (body)
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Column(
              children: [
                ListTile(
                  title: Text(prov.getPasien[0].saldo.toString(),
                    style: const TextStyle(
                      fontSize: 35,
                      fontWeight: FontWeight.bold,
                      color: Colors.black,
                    ),
                  ),
                    subtitle: const Text("Current Saldo")
                ),
                const Divider(),

                /// Header Text
                const Text(
                  "Add Saldo \n",
                  style: TextStyle(
                    fontSize: 25,
                  ),
                ),

                /// Dropdown for Saldo Picker
                DropdownButtonFormField<String>(
                  items: pilihanSaldo,
                  value: selectedSaldoStr,
                  onChanged: (value) {
                    setState(() {
                      selectedSaldo = int.parse(value!);
                      selectedSaldoStr = value;
                    });
                  },
                  decoration: InputDecoration(
                    labelText: "Saldo",
                    prefixIcon: const Icon(Icons.account_balance_wallet),
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                  ),
                ),
                const SizedBox(height: 20),
              ],
            ),

            /// Save Button
            Container(
              width: double.infinity,
              margin: const EdgeInsets.only(bottom: 30),
              child: ElevatedButton(
                onPressed: () =>
                    save(selectedSaldo!),
                child: const Text(
                  "Save",
                  style: TextStyle(
                    fontSize: 18,
                  ),
                ),
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(vertical: 15),
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