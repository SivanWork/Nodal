using System;
using System.Configuration;

namespace Nodal.Common.Helper
{
    public class ConfigurationHelper
    {
        private readonly AppSettingsReader configurationAppSettings;

        /// <summary>
        /// Implementation of the sigleton pattern
        /// </summary>
        public static ConfigurationHelper instance;

        /// <summary>
        /// Initializes a new instance of the <see cref="ConfigurationHelper"/> class. 
        /// default constructor
        /// </summary>
        public ConfigurationHelper()
        {
            this.configurationAppSettings = new System.Configuration.AppSettingsReader();
        }

        /// <summary>
        /// Singleton pattern constructor
        /// </summary>
        /// <returns>Configuration</returns>
        public static ConfigurationHelper GetInstance()
        {
            if (instance == null)
            {
                instance = new ConfigurationHelper();
            }

            return instance;
        }

        /// <summary>
        /// Returns a int for the config
        /// </summary>
        /// <param name="key">
        /// App.Settings key
        /// </param>
        /// <param name="type">
        /// Type of value to be returned
        /// </param>
        /// <param name="defaultValue">
        /// A default value if app.Setting is not configured or incorrect.
        /// </param>
        /// <returns>
        /// Returns a typed value for the app.Config
        /// </returns>
        public int GetConfig(string key, Type type, int defaultValue)
        {
            try
            {
                return int.Parse(this.configurationAppSettings.GetValue(key, type).ToString());
            }
            catch (Exception)
            {
                return defaultValue;
            }
        }

        /// <summary>
        /// Returns a string for the config
        /// </summary>
        /// <param name="key">
        /// App.Settings key
        /// </param>
        /// <param name="type">
        /// Type of value to be returned
        /// </param>
        /// <param name="defaultValue">
        /// A default value if app.Setting is not configured or incorrect.
        /// </param>
        /// <returns>
        /// Returns a typed value for the app.Config
        /// </returns>
        public string GetConfig(string key, Type type, string defaultValue)
        {
            try
            {
                return this.configurationAppSettings.GetValue(key, type).ToString();
            }
            catch (Exception)
            {
                return defaultValue;
            }
        }

        /// <summary>
        /// Returns a bool for the config
        /// </summary>
        /// <param name="key">
        /// App.Settings key
        /// </param>
        /// <param name="type">
        /// Type of value to be returned
        /// </param>
        /// <param name="defaultValue">
        /// A default value if app.Setting is not configured or incorrect.
        /// </param>
        /// <returns>
        /// Returns a typed value for the app.Config
        /// </returns>
        public bool GetConfig(string key, Type type, bool defaultValue)
        {
            try
            {
                return bool.Parse(this.configurationAppSettings.GetValue(key, type).ToString());
            }
            catch (Exception)
            {
                return defaultValue;
            }
        }
    }
}
