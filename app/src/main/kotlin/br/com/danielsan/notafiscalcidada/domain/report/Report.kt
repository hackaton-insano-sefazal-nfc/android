package br.com.danielsan.notafiscalcidada.domain.report

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by saturo on 18/08/17.
 */
data class Report(
        var id: Int = 0,
        var cNF: Int = 0,
        @SerializedName("cnpjDestinatario") var cnpjRecipient: String? = null,
        @SerializedName("cnpjEmitente") var cnpjIssuer: String = "",
        @SerializedName("cpfDestinatario") var cpfRecipient: String? = null,
        @SerializedName("dataEmissao") var dateOfIssue: String = "",
        @SerializedName("denuncia") var report: String = "",
        @SerializedName("numeroECF") var ecfNumber: String = "",
        @SerializedName("serie") var series: String = "",
        @SerializedName("situacao") var situation: String = "",
        @SerializedName("subSerie") var subSeries: Int = 0,
        @SerializedName("tipoDenuncia") var reportType: String = "",
        @SerializedName("tipoDocumento") var documentType: String = "",
        @SerializedName("valor") var value: Double = 0.0,
        @SerializedName("api_auth_token") var apiToken: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(id)
        writeInt(cNF)
        writeString(cnpjRecipient)
        writeString(cnpjIssuer)
        writeString(cpfRecipient)
        writeString(dateOfIssue)
        writeString(report)
        writeString(ecfNumber)
        writeString(series)
        writeString(situation)
        writeInt(subSeries)
        writeString(reportType)
        writeString(documentType)
        writeDouble(value)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Report> = object : Parcelable.Creator<Report> {
            override fun createFromParcel(source: Parcel): Report = Report(source)
            override fun newArray(size: Int): Array<Report?> = arrayOfNulls(size)
        }
    }

}
