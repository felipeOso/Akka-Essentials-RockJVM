package introduction

object MultithreadingRecap extends App {

  // creating threads on the jvm

  val aThread = new Thread(() => println("I'm running in parallel!"))

  //start Thread
  aThread.start()
  //wait for a thread to finish
  aThread.join()

  val threadHello = new Thread(() => (1 to 1000).foreach(_ => println("hello")))
  val threadGoodBye = new Thread(() => (1 to 1000).foreach(_ => println("goodBye")))

  /* differents runs produce differents results, it means each run generate a order different
  if you can check it, you should comment the threadsSync of below y run the following two lines*/
    threadHello.start()
    threadGoodBye.start()

  // differents runs produce the same results, because of threadHelloSync.join() wait threadHello finish, and
  //after execute threadGoodByeSyn
  val threadHelloSync = new Thread(() => (1 to 1000).foreach(_ => println("helloSync")))
  val threadGoodByeSync = new Thread(() => (1 to 1000).foreach(_ => println("goodByeSync")))

  threadHelloSync.start()
  threadHelloSync.join()
  threadGoodByeSync.start()

  class BankAccount(private var amount: Int){
    override def toString: String = "" + amount
    // in this expression is possible that two threads to be executed at the same time
    def withdraw(money:Int) = this.amount -= money

    // in this expression is Not possible that two threads to be executed at the same time
    def safeWithdraw(money:Int) = this.synchronized{
      this.amount -= money
    }
  }

  // inter-thread communication on JVM
  // wait - notify mechanism
}
