package models.users

import models.users.Account.currentYear

import java.time.Year

class Customers (val id : String,val firstName : String, val lastName : String,
                private var _address:List[String],
                private var _phone:String) {

  private var _accounts:List[Account] = Nil

  def accounts: List[Account] = _accounts

  def address:List[String] = _address
  def address_= (newAddr:List[String]):Unit = _address = newAddr

  def phone:String = _phone
  def phone_= (newPhone:String):Unit = _phone= newPhone

  def addAccount(account: Account):Boolean ={
    if (account.customer == this && !_accounts.exists(_.id == account.id)) {
      _accounts::= account
      true
    } else false
  }

  def removeAccount(accountID: String):Boolean = {
    val index = _accounts.indexWhere(_.id == accountID)
    if (index < 0) false
    else {
      _accounts = _accounts.patch(index,Nil,1)
      true
    }
  }


           }


object Customers{
  private var nextID= 1
  private val currentYear: Int = Year.now().getValue

  def apply(firstName:String,lastName:String,address:List[String],phone:String):Customers= {
    val id = currentYear.toString + "0" + nextID.toString.length + nextID
    nextID+= 1
    new Customers(id, firstName , lastName, address, phone)
  }
}
