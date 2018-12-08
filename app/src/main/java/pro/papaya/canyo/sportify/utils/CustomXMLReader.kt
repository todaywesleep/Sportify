package pro.papaya.canyo.sportify.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import java.io.InputStream
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import pro.papaya.canyo.sportify.model.Product

class CustomXMLReader(context: Context) {
  private val TAG = "[CustomXMLReader]"
  
  private val myInput: InputStream?
  private val assetManager: AssetManager = context.assets
  private val fileSystem: POIFSFileSystem
  private val myWorkBook: HSSFWorkbook
  private val activeSheet: HSSFSheet
  private var xmlContent: ArrayList<Product> = arrayListOf()

  init {
    myInput = assetManager.open("calories.xls")
    fileSystem = POIFSFileSystem(myInput)
    myWorkBook = HSSFWorkbook(fileSystem)
    activeSheet = myWorkBook.getSheetAt(0)

    getDocumentContent()
  }
  
  private fun getDocumentContent(){
    try {
      val rowIterator = activeSheet.rowIterator()
      var rowNumber = 0
      while (rowIterator.hasNext()) {
        val myRow = rowIterator.next() as HSSFRow
        if (rowNumber != 0) {
          val cellIterator = myRow.cellIterator()
          var colNumber = 0
          var productName = ""
          var squirrels = -1.0
          var fats = -1.0
          var carbohydrates = -1.0
          var calories = -1.0
          while (cellIterator.hasNext()) {
            val myCell = cellIterator.next() as HSSFCell

            when (colNumber){
              0 -> productName = myCell.toString()
              1 -> squirrels = myCell.toString().toDouble()
              2 -> fats = myCell.toString().toDouble()
              3 -> carbohydrates = myCell.toString().toDouble()
              4 -> calories = myCell.toString().toDouble()
            }

            colNumber++
          }

          val product = Product(productName, squirrels, fats, carbohydrates, calories)
          xmlContent.add(product)
        }

        rowNumber++
      }
    } catch (e: Exception) {
      Log.e(TAG, "error " + e.toString())
    }
  }

  public fun getXMLData(): ArrayList<Product>{
    return xmlContent
  }
}