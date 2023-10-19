import json
import sys 

if (len(sys.argv) < 2):
    exit(f"Usage: {sys.argv[0]} <output_file>")

outfile = sys.argv[1]

page = ""

title = "Welcome to Data Structures and Algorithms, Autumn 2023"

sec1 = (
"""
<h1 style="text-align: center;">
<span style="font-style: normal;"><strong style="font-family: inherit; font-size: 1.8em; color: var(--ic-brand-font-color-dark);">
"""
+ title +
"""
</strong><br /></span></h1>
<h2>Syllabus and Class Information</h2>
"""
)
page += sec1 

notices = """
<p>
  You can find our public class website with all information you need at:
</p>
<p><strong>
<a href="https://theevergreenstatecollege.github.io/upper-division-cs/dsa-23au/">https://theevergreenstatecollege.github.io/upper-division-cs/dsa-23au/</a>
</strong></p>
<p>
which is mirrored to our Canvas site at
</p>
<p><strong>
<a href="https://canvas.evergreen.edu/courses/5926">https://canvas.evergreen.edu/courses/5926</a>
</strong></p>.
<p>
  <span>
    <strong>Our first class is on Tuesday Sept. 26th at 10am (PST) in Evans 2617 (Computer Center)
    </strong><br />
  </span>
</p>
"""
page += notices

sec2 = (
"""
<div id="content-wrapper" class="ic-Layout-contentWrapper">
    <div id="content" class="ic-Layout-contentMain" role="main">
        <div id="course_home_content">
            <div id="wiki_page_show">
                <div class="show-content user_content clearfix enhanced">
"""
)
page += sec2 

# The script will render all of these wrapped with <p></p> tags
body_paragraphs = [
        """
        Data Structures and Algorithms is a 12-credit upper division applied Computer Science program that is important for doing any additional advanced work in computer science and software engineering. Some students in the program have already studied data structures (DS &amp; A). Those students will be creating their own curriculum in groups. For students who want to learn DS &amp; A, the program syllabus will look like this:
        """,
        """
        <ul>
            <li>Data Structures and Algorithms;</li>
            <li>Team programming projects (with ChangeMakers)</li>
            <li>Synthesis of real-world problems with software modeling</li>
            <li>Software Engineering</li>
        </ul>
        """,
        """
        As computer science students, you will deepen your technical knowledge and skills required to understand, analyze, modify, and build complex software systems. The concepts and skills from Data Structures and Algorithms are key for software engineering and programming. Through this program you will also deepen your understanding of computing systems, both in theory and practice. The work will provide prerequisite knowledge for Evergreen's advanced computer science programs.&nbsp;
        """,
        """
        Working on team projects is very important for almost any job, especially in computer science. Also, at Evergreen we expect students to take charge of their learning. We make this easier by providing&nbsp; a caring and safe innovative team environment. This is ideal for those looking for a practical, hands-on approach to learning. This radically different pathway gives students the opportunity to set-up and run their own projects and ventures. Inspired by the pioneering Finnish &lsquo;Tiimiakatemia&rsquo; approach, we designed this program collaboratively with the ChangeMaker Lab, which was the first to introduce this approach in the United States. The Tiimiakatemia ChangeMaker Lab is accredited by Tiimiakatemia Global&reg;.
        """,
        """
        In the first few weeks you will form a Team Company of up to 20 students, and work on real projects and research the programming, software engineering, and theoretical&nbsp; skills needed for those projects. You will gain entrepreneurial experience and learn how to operate and run an organization as a Holacratic structure. As a Team Entrepreneur, you&rsquo;ll work with your team in your own office environment virtually or in the classroom in our innovative open office space.&nbsp; You will create and manage projects around your passions, attend directors' meetings. You will have the opportunity to learn the auxiliary skills to manage budgets and build valuable contacts and networks.&nbsp;
        """,
        """
        You will learn by doing and specifically develop the following job skills: Self‐leadership skills, Project leading skills, Planning skills, Team Coaching skills, Strategic skills, Team leadership skills, Service, negotiation and selling skills, Understanding of financial issues, Marketing skills, Innovation skills, Team leadership skills, Data processing and IT &ndash;skills, Team Learning skills, Creativity skills, International skills and Communication skills and work on developing Initiative, Courage to make choices and goal orientation.
        """,
        """
        This full-time daytime program will be taught on the Olympia campus. Students who need to participate fully remotely should contact the faculty to discuss that option.
        """,
        """
        Computer technology has an impact on almost anything we do, and data structures and algorithms are central to advanced study in computer science and to building large complex systems. When applying for positions such as Software Developer or Software Engineer, you will probably find that this program is the most important in helping you prepare for job interviews. In this program, you&rsquo;ll learn about ways to organize data (we&rsquo;ll see various data structures such as Lists, Trees, Graphs), ways to compare algorithms (we&rsquo;ll focus on space and time complexity using big Oh) and efficiently solve programming problems. It will also give you a chance to practice object-oriented and functional programming.
        """,
        """
        We are so excited to work with all of you in this awesome program.
        """,
        """
        We expect everyone to attend in person, unless you contact us to make alternative arrangements.
        """
        ] 

page += "\n".join([f"<p>{x}</p>" for x in body_paragraphs])

CLASS_TIMES = [
    {
        "times": "10am-1pm",
        "Monday": {
            "event": "Data Structures",
            "room": "(Evans 2617)",
        },
        "Tuesday": {
            "event": "Systems and Teams",
            "room": "(Evans 2617)",
        },
        "Wednesday": {
            "event": "&nbsp;",
            "room": "",
        },
        "Thursday": {
            "event": "Data Structures",
            "room": "(Evans 2617)",
        },
        "Friday": {
            "event": "&nbsp;",
            "room": "",
        },
    },
    {
        "times": "12noon-1pm",
        "Monday": {
            "event": "&nbsp;",
            "room": "",
        },
        "Tuesday": {
            "event": "Forest Walk",
            "room": "",
        },
        "Wednesday": {
            "event": "&nbps;",
            "room": "",
        },
        "Thursday": {
            "event": "break",
            "room": "",
        },
        "Friday": {
            "event": "tutoring",
            "room": "",
        },
    }, {
        "times": "1-3pm",
        "Monday": {
            "event": "Cracking the Coding Interview / Software Engineering",
            "room": "(Evans 2617)",
        },
        "Tuesday": {
            "event": "ChangeMaker Lab",
            "room": "Sem2 C4105",
        },
        "Wednesday": {
            "event": "&nbsp;",
            "room": "",
        },
        "Thursday": {
            "event": "ChangeMaker Lab",
            "room": "Sem2 C4105",
        },
        "Friday": {
            "event": "&nbsp;",
            "room": "",
        }
    }, {
        "times": "3-5pm",
        "Monday": {
            "event": "&nbsp;",
            "room": "",
        },
        "Tuesday": {
            "event": "ChangeMaker Lab",
            "room": "Sem2 C4105",
        },
        "Wednesday": {
            "event": "&nbsp;",
            "room": "",
        },
        "Thursday": {
            "event": "ChangeMaker Lab",
            "room": "Sem2 C4105",
        },
        "Friday": {
            "event": "&nbsp;",
            "room": "",
        }
    }
] 

WIDTHS = {
  "times": 77,
  "Monday": 108,
  "Tuesday": 125,
  "Wednesday": 110,
  "Thursday": 111,
  "Friday": 104,
}

def render_schedule_body_cell(event, room, width):
    return (
        f'<td width="{width}">' +
        f'  <p>{event}</p>' +
        f'  <p>{room}</p>' + 
        f'</td>'
        )

def render_schedule_header_cell(day, width):
    return (
        f'<th class="col" width="{width}">' +
        f'  <p>{day}</p>' + 
        f'</th>' +
        "\n"
        )

DAYS = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
]

def render_schedule_header_row():
    return (
        "<tr>" +
        "\n".join([ render_schedule_header_cell(day, WIDTHS[day]) for day in DAYS ]) +
        "</tr>\n" 
    )    

def render_schedule_body_row(row):
  return (
      "<tr>" +
      f'  <td>{row["times"]}' +
      "\n".join([
          render_schedule_body_cell(row[day]["event"], row[day]["room"], WIDTHS[day]) for day in DAYS
      ]) +
      "</tr>\n"
      )    

class_schedule = (
    """
    <h3>Class Meeting Times</h3>
    <table border="1" width="0">
        <tbody>
    """ +
    render_schedule_header_row() +
    "\n".join([ render_schedule_body_row(row) for row in CLASS_TIMES ]) +
    """
       </tbody>
    </table>
    """
)

page += class_schedule

page += (
"""
                    <p>&nbsp;</p>
                    <p>Textbooks:</p>
                    <p><strong><span>Required</span></strong></p>
                    <ul>
                        <li><span>The Algorithm Design Manual, 3rd edition, by Steven Skiena ()</span></li>
                        <li><span>Cracking the Coding Interview &ndash; 189 programming questions and solutions, Gayle Laakmann McDowell, 6th edition (CCI)<br /><span class="a-size-base a-color-base a-text-bold">ISBN-13:</span>&nbsp;<span class="a-size-base a-color-base">978-0984782857</span>&nbsp;</span></li>
                    </ul>
                    <p><span>Optional</span></p>
                    <ul>
                        <li><span>Data Structures and Algorithms in Java, 3rd or 4th edition, by Mark Allen Weiss</span></li>
                        <li><span class="NormalTextRun SCXW197021631 BCX0">Dive into Systems (DIS)&nbsp;<span>&nbsp;</span><span><a class="external" href="https://diveintosystems.org/development/antora/diveintosystems/1.0/index.html" target="_blank" rel="noopener">Dive Into Systems Text: https://diveintosystems.org/development/antora/diveintosystems/1.0/index.html</a></span></span></li>
                        <li>Parallel computing for Beginners https://www.learnpdc.org/PDCBeginners2e/</li>
                    </ul>
"""
)

def generate_track_rows(tracks):
    return (
"        <tr>\n" +
"          <td></td>\n" + 
           ''.join([f"            <td width=\"{track['width']}\">{track['name']}</td>\n" for track in tracks]) +
"        </tr>\n"
            )

def generate_topic_row(weekNum, track1List, track2List):
    return (
 "        <tr>\n" +
f"          <td>Week {weekNum}</td>\n" +
 "          <td>\n" +
           ''.join([f"            <p>{topic}</p>\n" for topic in track1List]) +
"           </td>\n" +
"           <td>\n" +
           ''.join([f"            <p>{topic}</p>\n" for topic in track2List]) +
"           </td>\n" +
"         </tr>\n"
            )

with open('./topics-list.json') as f:
    lines = f.readlines()
    jsonObj = json.loads(''.join(lines))
    tracks = jsonObj["tracks"]

    weeks = jsonObj["weeks"]

    # Table header
    page += (
"""
                    <table border="1">
                        <tbody>
""" + generate_track_rows(tracks) + 
''.join([ generate_topic_row(key, value['first'], value['second']) for (key,value) in weeks.items() ]) +
"</tbody>" +
"</table>" +
"""
                </div>
            </div>
        </div>
    </div>
</div>
""")

page += (
"""
<h3>Tracks</h3>

<p>
Student who have never taken DSA before will take DSA in this quarter and participate in Changemakers team companies.
They may choose either the Java programming language (with examples and support provided by DSA assignments) or any 
other programming language where they must provide their own support and collaborate with each other.
For example, if you choose Rust, you need to find at least one other classmate who wants to do Rust and check in with 
each other.
These students will be evaluated by Richard with feedback from Paul.
</p>

<p>
Students who have already taken DSA will be doing an Independent Learning Contract, with a self-designed 
curriculum, weekly milestones, and a statement of deliverables. These students will be evaluated by Paul
with feedback from Richard, and will be asked to give updates on their progress and share feedback with
the class each Thursday morning except the first.
</p>

<h3>Evaluation</h3>
<p>
Depending on which track of the class you are doing, you will be evaluated by a different faculty.
If in doubt, please ask either Richard or Paul.
<p>
<ul>
  <li>Students in the DSA and Changemakers track will be evaluated by Richard, with feedback from Paul.</li>
  <li>Students in the Independent Learning Contract track will be evaluated by Paul, with feedback from Richard.</li>
</ul>

<h4>Paul's Feedback Criteria</h4>
<p>
For students in both tracks, Paul will assign *quantity* of credits (totalling 12 or 16 as agreed upon after the first week)
based on amount of work, and write a narrative evaluation based on the quality of work and concepts demonstrated.
</p>

<p>Quantity of Credits</p>
<ul>
  <li>33% from completing weekly DSA assignments, or completing weekly milestones.</li>
  <li>33% from participating in class activities and GitHub contributions to the class monorepo, especially merging pull requests.</li>
  <li>33% from final project, which may be your Changemakers' team or individual project, or your Independent Learning Contract.</li> 
</ul>
"""
)

page += (
"""
<div id="right-side-wrapper" class="ic-app-main-content__secondary">
    <aside id="right-side" role="complementary">
        <div id="course_show_secondary">
            <div class="course-options"><a class="btn button-sidebar-wide" href="https://canvas.evergreen.edu/courses/5162?view=feed"><i class="icon-stats"></i><span>&nbsp;</span>View Course Stream</a><a class="btn button-sidebar-wide" href="https://canvas.evergreen.edu/courses/5162/analytics"><i class="icon-analytics" role="presentation"></i><span>&nbsp;</span>View Course Analytics</a></div>
            <a class="btn button-sidebar-wide" href="https://canvas.evergreen.edu/courses/5162?view=notifications"><i class="icon-unmuted"></i><span>&nbsp;</span>View Course Notifications</a>
        </div>
        <div class="events_list
  coming_up">
            <div class="h2 shared-space">
                <h2>Coming Up</h2>
                <a class="event-list-view-calendar icon-calendar-day standalone-icon" href="https://canvas.evergreen.edu/calendar?include_contexts=course_5162"><span>&nbsp;</span>View Calendar</a>
            </div>
            <ul class="right-side-list events">
                <li><small>Nothing for the next week</small></li>
            </ul>
        </div>
    </aside>
</div>
<p style="text-align: center;">&nbsp;</p>
<h2 style="text-align: center;"><a id=" " class="Button Button--primary" title="Modules List" href="https://canvas.evergreen.edu/courses/5926/modules" data-api-endpoint="https://canvas.evergreen.edu/api/v1/courses/5926/modules" data-api-returntype="[Module]">START HERE</a></h2>
<p style="text-align: center;">Or click on <a id="" class="" title="Modules List" href="https://canvas.evergreen.edu/courses/5926/modules" target="" data-api-endpoint="https://canvas.evergreen.edu/api/v1/courses/5926/modules" data-api-returntype="[Module]">Modules</a> in menu at left to view list.</p>
"""
)

with open(outfile, "w") as f:
    f.write(page)

