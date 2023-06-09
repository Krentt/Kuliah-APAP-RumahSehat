import 'package:flutter/material.dart';
import 'package:jwt_decode/jwt_decode.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/add_appotintment_page.dart';
import 'package:rumah_sehat_app/pages/home_page.dart';
import 'package:rumah_sehat_app/pages/list_appointments_page.dart';

import '../pages/pasien_profile.dart';
import '../pages/pasien_tagihan_list.dart';
import '../providers/auth.dart';

class Account with ChangeNotifier {
  String? token;
  String? username;
  String? email;

  void updateData(tokenData) {
    token = tokenData;
    notifyListeners();
  }

  Future<void> getAccount() async {
    Map<String, dynamic> payload = Jwt.parseJwt(token.toString());
    username = payload["USERNAME"];
    email = payload["EMAIL"];
  }

}

class DrawerScreen extends StatefulWidget {
  const DrawerScreen({Key? key}) : super(key: key);

  @override
  _DrawerScreenState createState() => _DrawerScreenState();
}

class _DrawerScreenState extends State<DrawerScreen> {
  @override
  Widget build(BuildContext context) {
    Provider.of<Account>(context, listen: false).getAccount();
    final prov = Provider.of<Account>(context);
    return Drawer(
      child: SafeArea(
        child: Column(
          children: [
            UserAccountsDrawerHeader(
              accountName: Text(
                prov.username.toString(),
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
              ),
              accountEmail: Text(prov.email.toString(), style: TextStyle(fontSize: 15),),
            ),
            GestureDetector(
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => HomePage()));
              },
              child: ListTile(
                leading: Icon(
                  Icons.home,
                  size: 25,
                  color: Colors.black,
                ),
                title: Text(
                  "Home",
                  softWrap: true,
                  style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.w400,
                  ),
                ),
              ),
            ),
            GestureDetector(
              onTap: () => Navigator.pushNamed(context, PasienProfile.route),
              child: ListTile(
                leading: Icon(
                  Icons.account_circle,
                  size: 25,
                  color: Colors.black,
                ),
                title: Text(
                  "My Profile",
                  softWrap: true,
                  style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.w400,
                  ),
                ),
              ),
            ),
            GestureDetector(
              onTap: () =>
                  Navigator.pushNamed(context, AddAppointmentPage.route),
              child: ListTile(
                leading: Icon(
                  Icons.add,
                  size: 25,
                  color: Colors.black,
                ),
                title: Text(
                  "Create Appointment",
                  softWrap: true,
                  style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.w400,
                  ),
                ),
              ),
            ),
            GestureDetector(
              onTap: () => Navigator.pushNamed(context, ListAppointments.route),

              child: ListTile(
                leading: Icon(
                  Icons.today,
                  size: 25,
                  color: Colors.black,
                ),
                title: Text(
                  "Appointments",
                  softWrap: true,
                  style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.w400,
                  ),
                ),
              ),
            ),
            GestureDetector(
              onTap: () => Navigator.pushNamed(context, PasienTagihan.route),
              child: ListTile(
                leading: Icon(
                  Icons.payment,
                  size: 25,
                  color: Colors.black,
                ),
                title: Text(
                  "My Bills",
                  softWrap: true,
                  style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.w400,
                  ),
                ),
              ),
            ),
            GestureDetector(
              onTap: () => Provider.of<Authh>(context, listen: false).logout(),
              child: ListTile(
                leading: Icon(
                  Icons.logout,
                  size: 25,
                  color: Colors.black,
                ),
                title: Text(
                  "Logout",
                  softWrap: true,
                  style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.w400,
                  ),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
