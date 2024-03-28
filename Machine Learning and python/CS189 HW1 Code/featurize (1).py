'''
**************** PLEASE READ ***************

Script that reads in spam and ham messages and converts each training example
into a feature vector

Code intended for UC Berkeley course CS 189/289A: Machine Learning

Requirements:
-scipy ('pip install scipy')

To add your own features, create a function that takes in the raw text and
word frequency dictionary and outputs a int or float. Then add your feature
in the function 'def generate_feature_vector'

The output of your file will be a .mat file. The data will be accessible using
the following keys:
    -'training_data'
    -'training_labels'
    -'test_data'

Please direct any bugs to kevintee@berkeley.edu
'''

from collections import defaultdict
import glob
import re
import scipy.io
import numpy as np
import pdb

NUM_TRAINING_EXAMPLES = 4172
NUM_TEST_EXAMPLES = 1000

BASE_DIR = '../data/'
SPAM_DIR = 'spam/'
HAM_DIR = 'ham/'
TEST_DIR = 'test/'

# ************* Features *************

# Features that look for certain words
def freq_pain_feature(text, freq):
    return float(freq['pain'])

def freq_private_feature(text, freq):
    return float(freq['private'])

def freq_bank_feature(text, freq):
    return float(freq['bank'])

def freq_money_feature(text, freq):
    return float(freq['money'])

def freq_drug_feature(text, freq):
    return float(freq['drug'])

def freq_spam_feature(text, freq):
    return float(freq['spam'])

def freq_prescription_feature(text, freq):
    return float(freq['prescription'])

def freq_creative_feature(text, freq):
    return float(freq['creative'])

def freq_height_feature(text, freq):
    return float(freq['height'])

def freq_featured_feature(text, freq):
    return float(freq['featured'])

def freq_differ_feature(text, freq):
    return float(freq['differ'])

def freq_width_feature(text, freq):
    return float(freq['width'])

def freq_other_feature(text, freq):
    return float(freq['other'])

def freq_energy_feature(text, freq):
    return float(freq['energy'])

def freq_business_feature(text, freq):
    return float(freq['business'])

def freq_message_feature(text, freq):
    return float(freq['message'])

def freq_volumes_feature(text, freq):
    return float(freq['volumes'])

def freq_revision_feature(text, freq):
    return float(freq['revision'])

def freq_path_feature(text, freq):
    return float(freq['path'])

def freq_meter_feature(text, freq):
    return float(freq['meter'])

def freq_memo_feature(text, freq):
    return float(freq['memo'])

def freq_planning_feature(text, freq):
    return float(freq['planning'])

def freq_pleased_feature(text, freq):
    return float(freq['pleased'])

def freq_record_feature(text, freq):
    return float(freq['record'])

def freq_out_feature(text, freq):
    return float(freq['out'])

# Features that look for certain characters
def freq_semicolon_feature(text, freq):
    return text.count(';')

def freq_dollar_feature(text, freq):
    return text.count('$')

def freq_sharp_feature(text, freq):
    return text.count('#')

def freq_exclamation_feature(text, freq):
    return text.count('!')

def freq_para_feature(text, freq):
    return text.count('(')

def freq_bracket_feature(text, freq):
    return text.count('[')

def freq_and_feature(text, freq):
    return text.count('&')

# --------- Add your own feature methods ----------

#Should I delete some?
def freq_font_feature(text, freq):
    return float(freq['font'])
    
def freq_br_feature(text, freq):
    return float(freq['br'])
    
def freq_td_feature(text, freq):
    return float(freq['td'])

def freq_color_feature(text, freq):
    return float(freq['color'])

def freq_moopid_feature(text, freq):
    return float(freq['moopid'])

def freq_hotlist_feature(text, freq):
    return float(freq['hotlist'])

def freq_pt_feature(text, freq):
    return float(freq['pt'])

def freq_style_feature(text, freq):
    return float(freq['style'])

def freq_size_feature(text, freq):
    return float(freq['size'])

def freq_family_feature(text, freq):
    return float(freq['family'])

def freq_line_feature(text, freq):
    return float(freq['line'])

def freq_knle_feature(text, freq):
    return float(freq['knle'])

def freq_calls_feature(text, freq):
    return float(freq['calls'])

def freq_face_feature(text, freq):
    return float(freq['face'])

def freq_hottlist_feature(text, freq):
    return float(freq['hottlist'])

def freq_dosage_feature(text, freq):
    return float(freq['dosage'])

def freq_valign_feature(text, freq):
    return float(freq['valign'])

def freq_align_feature(text, freq):
    return float(freq['align'])

def freq_div_feature(text, freq):
    return float(freq['div'])

def freq_pills_feature(text, freq):
    return float(freq['pills'])

def freq_abdv_feature(text, freq):
    return float(freq['abdv'])

def freq_border_feature(text, freq):
    return float(freq['border'])

def freq_gif_feature(text, freq):
    return float(freq['gif'])

def freq_middle_feature(text, freq):
    return float(freq['middle'])

def freq_china_feature(text, freq):
    return float(freq['china'])

def freq_itoy_feature(text, freq):
    return float(freq['itoy'])

def freq_strong_feature(text, freq):
    return float(freq['strong'])

def freq_colspan_feature(text, freq):
    return float(freq['colspan'])

def freq_price_feature(text, freq):
    return float(freq['price'])

def freq_international_feature(text, freq):
    return float(freq['international'])

def freq_competitive_feature(text, freq):
    return float(freq['competitive'])

def freq_portugal_feature(text, freq):
    return float(freq['portugal'])

def freq_aerofoam_feature(text, freq):
    return float(freq['aerofoam'])

def freq_statements_feature(text, freq):
    return float(freq['statements'])

def freq_dcenterfont_feature(text, freq):
    return float(freq['dcenterfont'])

def freq_darial_feature(text, freq):
    return float(freq['darial'])

def freq_htmlimg_feature(text, freq):
    return float(freq['htmlimg'])

def freq_events_feature(text, freq):
    return float(freq['events'])

def freq_download_feature(text, freq):
    return float(freq['download'])

def freq_account_feature(text, freq):
    return float(freq['account'])

def freq_handheldmed_feature(text, freq):
    return float(freq['handheldmed'])

def freq_tracker_feature(text, freq):
    return float(freq['tracker'])

def freq_patient_feature(text, freq):
    return float(freq['patient'])

def freq_istorage_feature(text, freq):
    return float(freq['istorage'])

def freq_storage_feature(text, freq):
    return float(freq['storage'])

def freq_hospital_feature(text, freq):
    return float(freq['hospital'])

def freq_dvd_feature(text, freq):
    return float(freq['dvd'])

def freq_cryptogram_feature(text, freq):
    return float(freq['cryptogram'])

def freq_cockcrow_feature(text, freq):
    return float(freq['cockcrow'])

def freq_crib_feature(text, freq):
    return float(freq['crib'])

def freq_bingo_feature(text, freq):
    return float(freq['bingo'])

def freq_trade_feature(text, freq):
    return float(freq['trade'])

def freq_credential_feature(text, freq):
    return float(freq['credential'])

def freq_tampon_feature(text, freq):
    return float(freq['tampon'])

def freq_viagra_feature(text, freq):
    return float(freq['viagra'])

def freq_surprised_feature(text, freq):
    return float(freq['surprised'])

def freq_cocksuckers_feature(text, freq):
    return float(freq['cocksuckers'])

def freq_male_feature(text, freq):
    return float(freq['male'])

def freq_women_feature(text, freq):
    return float(freq['women'])

def freq_ass_feature(text, freq):
    return float(freq['ass'])

def freq_cum_feature(text, freq):
    return float(freq['cum'])

def freq_atlantis_feature(text, freq):
    return float(freq['atlantis'])

def freq_rental_feature(text, freq):
    return float(freq['rental'])

def freq_high_feature(text, freq):
    return float(freq['high'])

def freq_sirius_feature(text, freq):
    return float(freq['sirius'])

def freq_http_feature(text, freq):
    return float(freq['http'])

def freq_tr_feature(text, freq):
    return float(freq['tr'])

def freq_spain_feature(text, freq):
    return float(freq['spain'])

def freq_hellosoft_feature(text, freq):
    return float(freq['hellosoft'])

def freq_wifi_feature(text, freq):
    return float(freq['wifi'])

def freq_earthquake_feature(text, freq):
    return float(freq['earthquake'])

def freq_explore_feature(text, freq):
    return float(freq['explore'])

def freq_cellpadding_feature(text, freq):
    return float(freq['cellpadding'])

def freq_brbr_feature(text, freq):
    return float(freq['brbr'])

def freq_nbsp_feature(text, freq):
    return float(freq['nbsp'])

# Generates a feature vector
def generate_feature_vector(text, freq):
    feature = []
    feature.append(freq_pain_feature(text, freq))
    feature.append(freq_private_feature(text, freq))
    feature.append(freq_bank_feature(text, freq))
    feature.append(freq_money_feature(text, freq))
    feature.append(freq_drug_feature(text, freq))
    feature.append(freq_spam_feature(text, freq))
    feature.append(freq_prescription_feature(text, freq))
    feature.append(freq_creative_feature(text, freq))
    feature.append(freq_height_feature(text, freq))
    feature.append(freq_featured_feature(text, freq))
    feature.append(freq_differ_feature(text, freq))
    feature.append(freq_width_feature(text, freq))
    feature.append(freq_other_feature(text, freq))
    feature.append(freq_energy_feature(text, freq))
    feature.append(freq_business_feature(text, freq))
    feature.append(freq_message_feature(text, freq))
    feature.append(freq_volumes_feature(text, freq))
    feature.append(freq_revision_feature(text, freq))
    feature.append(freq_path_feature(text, freq))
    feature.append(freq_meter_feature(text, freq))
    feature.append(freq_memo_feature(text, freq))
    feature.append(freq_planning_feature(text, freq))
    feature.append(freq_pleased_feature(text, freq))
    feature.append(freq_record_feature(text, freq))
    feature.append(freq_out_feature(text, freq))
    feature.append(freq_semicolon_feature(text, freq))
    feature.append(freq_dollar_feature(text, freq))
    feature.append(freq_sharp_feature(text, freq))
    feature.append(freq_exclamation_feature(text, freq))
    feature.append(freq_para_feature(text, freq))
    feature.append(freq_bracket_feature(text, freq))
    feature.append(freq_and_feature(text, freq))

    # --------- Add your own features here ---------
    # Make sure type is int or float

    feature.append(freq_font_feature(text, freq))
    feature.append(freq_br_feature(text, freq))
    feature.append(freq_td_feature(text, freq))
    feature.append(freq_color_feature(text, freq))
    feature.append(freq_moopid_feature(text, freq))
    feature.append(freq_hotlist_feature(text, freq))
    feature.append(freq_pt_feature(text, freq))
    feature.append(freq_style_feature(text, freq))
    feature.append(freq_size_feature(text, freq))
    feature.append(freq_family_feature(text, freq))
    feature.append(freq_line_feature(text, freq))
    feature.append(freq_knle_feature(text, freq))
    feature.append(freq_calls_feature(text, freq))
    feature.append(freq_face_feature(text, freq))
    feature.append(freq_hottlist_feature(text, freq))
    feature.append(freq_dosage_feature(text, freq))
    feature.append(freq_valign_feature(text, freq))
    feature.append(freq_align_feature(text, freq))
    feature.append(freq_div_feature(text, freq))
    feature.append(freq_pills_feature(text, freq))
    feature.append(freq_abdv_feature(text, freq))
    feature.append(freq_border_feature(text, freq))
    feature.append(freq_gif_feature(text, freq))
    feature.append(freq_middle_feature(text, freq))
    feature.append(freq_china_feature(text, freq))
    feature.append(freq_itoy_feature(text, freq))
    feature.append(freq_strong_feature(text, freq))
    feature.append(freq_colspan_feature(text, freq))
    feature.append(freq_price_feature(text, freq))
    feature.append(freq_international_feature(text, freq))
    feature.append(freq_competitive_feature(text, freq))
    feature.append(freq_portugal_feature(text, freq))
    feature.append(freq_aerofoam_feature(text, freq))
    feature.append(freq_statements_feature(text, freq))
    feature.append(freq_dcenterfont_feature(text, freq))
    feature.append(freq_darial_feature(text, freq))
    feature.append(freq_htmlimg_feature(text, freq))
    feature.append(freq_events_feature(text, freq))
    feature.append(freq_download_feature(text, freq))
    feature.append(freq_account_feature(text, freq))
    feature.append(freq_handheldmed_feature(text, freq))
    feature.append(freq_tracker_feature(text, freq))
    feature.append(freq_patient_feature(text, freq))
    feature.append(freq_istorage_feature(text, freq))
    feature.append(freq_storage_feature(text, freq))
    feature.append(freq_hospital_feature(text, freq))
    feature.append(freq_dvd_feature(text, freq))
    feature.append(freq_cryptogram_feature(text, freq))
    feature.append(freq_cockcrow_feature(text, freq))
    feature.append(freq_crib_feature(text, freq))
    feature.append(freq_bingo_feature(text, freq))
    feature.append(freq_trade_feature(text, freq))
    feature.append(freq_credential_feature(text, freq))
    feature.append(freq_viagra_feature(text, freq))
    feature.append(freq_surprised_feature(text, freq))
    feature.append(freq_cocksuckers_feature(text, freq))
    feature.append(freq_male_feature(text, freq))
    feature.append(freq_women_feature(text, freq))
    feature.append(freq_ass_feature(text, freq))
    feature.append(freq_cum_feature(text, freq))
    feature.append(freq_atlantis_feature(text, freq))
    feature.append(freq_rental_feature(text, freq))
    feature.append(freq_high_feature(text, freq))
    feature.append(freq_sirius_feature(text, freq))
    feature.append(freq_http_feature(text, freq))
    feature.append(freq_tr_feature(text, freq))
    feature.append(freq_spain_feature(text, freq))
    feature.append(freq_hellosoft_feature(text, freq))
    feature.append(freq_wifi_feature(text, freq))
    feature.append(freq_earthquake_feature(text, freq))
    feature.append(freq_explore_feature(text, freq))
    feature.append(freq_cellpadding_feature(text, freq))
    feature.append(freq_brbr_feature(text, freq))
    feature.append(freq_nbsp_feature(text, freq))
    
     



    
    
    return feature

# This method generates a design matrix with a list of filenames
# Each file is a single training example
def generate_design_matrix(filenames):
    most_freq_words = []
    design_matrix = []
    for filename in filenames:
        with open(filename, 'r', encoding='utf-8', errors='ignore') as f:
            try:
                text = f.read() # Read in text from file
            except Exception as e:
                # skip files we have trouble reading.
                continue
            text = text.replace('\r\n', ' ') # Remove newline character
            words = re.findall(r'\w+', text)
            word_freq = defaultdict(int) # Frequency of all words
            for word in words:
                word_freq[word] += 1
                if (word_freq[word] > 10 and (word not in most_freq_words)):
                    most_freq_words.append(word)

            # Create a feature vector
            feature_vector = generate_feature_vector(text, word_freq)
            design_matrix.append(feature_vector)
    '''
    print(most_freq_words)   
    print(len(most_freq_words))   
    '''
    return design_matrix
    #return design_matrix, most_freq_words


# ************** Script starts here **************
# DO NOT MODIFY ANYTHING BELOW

spam_filenames = glob.glob(BASE_DIR + SPAM_DIR + '*.txt')
spam_design_matrix = generate_design_matrix(spam_filenames)
ham_filenames = glob.glob(BASE_DIR + HAM_DIR + '*.txt')
ham_design_matrix = generate_design_matrix(ham_filenames)
# Important: the test_filenames must be in numerical order as that is the
# order we will be evaluating your classifier
test_filenames = [BASE_DIR + TEST_DIR + str(x) + '.txt' for x in range(NUM_TEST_EXAMPLES)]
test_design_matrix = generate_design_matrix(test_filenames)

X = spam_design_matrix + ham_design_matrix
Y = np.array([1]*len(spam_design_matrix) + [0]*len(ham_design_matrix)).reshape((-1, 1)).squeeze()

np.savez(BASE_DIR + 'spam-data.npz', training_data=X, training_labels=Y, test_data=test_design_matrix)

     
