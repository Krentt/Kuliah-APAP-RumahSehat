import 'package:flutter/material.dart';
import 'package:rumah_sehat_app/pages/add_appotintment_page.dart';
import 'package:rumah_sehat_app/pages/home_page.dart';
import 'package:provider/provider.dart';
import '../providers/auth.dart';

class DrawerScreen extends StatefulWidget {
  const DrawerScreen({Key? key}) : super(key: key);

  @override
  _DrawerScreenState createState() => _DrawerScreenState();
}

class _DrawerScreenState extends State<DrawerScreen> {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: SafeArea(
        child: Column(
          children: [
            const UserAccountsDrawerHeader(
              accountName: Text("Soobin gantenkk"),
              accountEmail: Text("ayanknina@gmail.com"),
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
              onTap: () {
                // Navigator.push(context,MaterialPageRoute(builder: (context)=> ViewMyProfile()));
              },
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
              onTap: () =>  Navigator.pushNamed(context, AddAppointmentPage.route),
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
              onTap: () {
                // Navigator.push(context,MaterialPageRoute(builder: (context)=> ViewAllAppointment()));
              },
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
              onTap: () {
                // Navigator.push(context,MaterialPageRoute(builder: (context)=> ViewTagihan()));
              },
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
