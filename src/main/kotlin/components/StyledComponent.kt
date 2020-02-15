package components

import kotlin.browser.window
import kotlinx.css.*
import kotlinx.css.Color.Companion.darkGray
import kotlinx.css.TextAlign.center
import react.*
import react.dom.*
import styled.*

private fun component(): FunctionalComponent<RProps> {
    val (count, setCount) = useState(0)

    return functionalComponent {

        useEffect(listOf(count)) {
            window.setTimeout({
                setCount(count+1)
            }, 1000)
        }

        styledDiv {
            css {
                textAlign = center
                backgroundColor = darkGray
                borderRadius = 15.px
                margin = "5em"
                padding = "2em"
            }
            h4 {
                +"Hello, react with kotlin. "
                +"Passed $count seconds"
            }
        }
    }
}

fun RBuilder.styledComponent(): ReactElement {
    return child(component(), object : RProps {}, listOf())
}