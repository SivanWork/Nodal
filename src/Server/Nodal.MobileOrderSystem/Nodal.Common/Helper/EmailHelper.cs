using System;
using System.ComponentModel;
using System.Net;
using System.Net.Mail;

namespace Nodal.Common.Helper
{
    public static class EmailHelper
    {
        private static void SendCompletedCallback(object sender, AsyncCompletedEventArgs e)
        {
            // Get the unique identifier for this asynchronous operation.
            String token = (string)e.UserState;

            if (e.Cancelled)
            {
                // Log
                Console.WriteLine("[{0}] Send canceled.", token);
            }
            if (e.Error != null)
            {
                // Log the error
                Console.WriteLine("[{0}] {1}", token, e.Error.ToString());
            }
            else
            {
                // Log on Success
                Console.WriteLine("Message sent.");
            }
        }

        public static void Send(string sender, string password, string recipient, string subject, string messageBody, Attachment arrachment = null)
        {
            try
            {
                SmtpClient client = new SmtpClient(ConfigurationHelper.GetInstance().GetConfig("SMTP", typeof(string), "smtp.gmail.com"));
                client.Port = Convert.ToInt32(ConfigurationHelper.GetInstance().GetConfig("SmtpPort", typeof(string), "587"));
                client.DeliveryMethod = SmtpDeliveryMethod.Network;
                client.UseDefaultCredentials = false;
                NetworkCredential credentials = new NetworkCredential(sender, password);
                client.EnableSsl = true;
                client.Credentials = credentials;

                MailAddress from = new MailAddress(sender.Trim(), "ORDA Web Portal - Auto Generated", System.Text.Encoding.UTF8);
                MailAddress to = new MailAddress(recipient.Trim());

                MailMessage message = new MailMessage(from, to);
                message.Subject = subject;
                message.SubjectEncoding = System.Text.Encoding.UTF8;
                if (arrachment != null) message.Attachments.Add(arrachment);
                message.Body = messageBody;
                message.BodyEncoding = System.Text.Encoding.UTF8;

                client.SendCompleted += new SendCompletedEventHandler(SendCompletedCallback);
                string userState = "Report Email";
                client.SendAsync(message, userState);
            }
            catch (Exception)
            {
                // Log the exception
            }
        }
    }
}
